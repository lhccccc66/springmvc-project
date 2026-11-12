# 用户管理模块(springmvc-project)

> 基于 Spring Boot 3.5 + SpringMVC + MyBatis 的后台用户管理模块
> 大二上课程项目,是后续「进销存项目」的**技术底座**(统一响应 / 全局异常 / 拦截器 / 分层模板直接复用)

## 技术栈

| 技术 | 版本 | 用途 |
|---|---|---|
| JDK | 17 | |
| Spring Boot | 3.5.11 | 框架 |
| SpringMVC | 内置 | Web 层,注解开发 REST 接口 |
| Spring IOC | 内置 | 依赖注入 + 三层架构 |
| MySQL | 8.x | 持久层(`db_demo_crud`) |
| **MyBatis** | **3.0.4** | **ORM,手写 XML + 动态 SQL** |
| MyBatis-Plus | 学习中 | ORM,替代手写 XML CRUD |
| Maven | 3.9.11 | 依赖管理 |
| Lombok | | `@Data` 自动生成 getter/setter |
| Validation | jakarta.validation | `@Valid` + JSR-380 参数校验 |
| Thymeleaf | | 页面模板(forward 跳转) |

## 功能清单

- [x] 用户列表条件查询(用户名 / 手机号 / 状态 三个可选条件)
- [x] 用户详情查询(`/user/{id}`)
- [x] 新增用户(`@Valid` + `BindingResult` 参数校验)
- [x] 修改用户(动态 `<set>`,null 字段不参与更新)
- [x] 删除用户
- [x] 登录 / 注销(Session 维持登录态)
- [x] 登录拦截器(未登录拦截,静态资源放行)
- [x] 统一响应格式 `Result`
- [x] 全局异常处理(`@RestControllerAdvice` + `@ExceptionHandler`)
- [x] 参数校验注解(`@NotBlank` / `@Size` / `@Pattern` / `@Min` / `@Max`)
- [x] **MyBatis 持久层接入**(手写 XML,`#{}` 预编译防注入)
- [x] **动态 SQL**(`<where>` 自动去多余 AND、`<set>` 自动去多余逗号)
- [x] **一对一关联映射**(`resultMap` + `association`)
- [x] **一对多关联映射**(`resultMap` + `collection`,多行自动合并成一个父对象)
- [x] 单元测试(`@SpringBootTest` 真实连库执行 SQL)
- [ ] 密码 BCrypt 加密存储
- [ ] MyBatis-Plus 接入(用 `BaseMapper` 替代手写 XML)

## 三层架构

```
Controller(接参 / 调 Service / 返 Result)
    ↓
Service(业务逻辑 / 事务边界)
    ↓
Mapper(数据访问,MyBatis 接口 + XML)
```

```
com.atguigu.springmvc
├── controller/      SysUserController、LoginController、ResponseController...
├── service/         SysUserService + Impl、LoginService + Impl
├── mapper/          SysUserMapper、OrderMapper、CustomerMapper(+ 同名 XML)
├── entities/        SysUser、Order、Customer
├── dto/             SysUserLoginDto
├── interceptor/     LoginProtectInterceptor、Demo01/02Interceptor
├── config/          InterceptorConfig、LoginInterceptorConfig
├── exception/       GlobalExceptionHandler
└── util/            Result(统一响应)、SysUserDataUtil
resources/mapper/    SysUserMapper.xml、OrderMapper.xml、CustomerMapper.xml
src/test/            OrderMapperTest、CustomerMapperTest(真实连库)
```

**分层原则**:
- Controller 不碰数据,只做协议转换
- Service 不出现 `HttpServletRequest`,业务可脱离 Web 复用
- Mapper 不做业务判断,只存取数据
- **事务必须标在 Service 层**(`@Transactional` 基于 AOP 代理,标在 Controller 上不生效)

## 亮点(面试可讲)

1. **为什么要分层?** —— 职责分离、解耦复用、便于单元测试;且 Spring 事务必须落在 Service 层
2. **拦截器和 Filter 的区别?** —— Filter 属 Servlet 规范管不了 Controller 方法;Interceptor 属 SpringMVC 规范,能拿到 HandlerMethod。多个拦截器按洋葱模型执行(preHandle 正序 / postHandle 逆序)
3. **`@Autowired` vs `@Resource`?** —— 前者 Spring 提供、按类型、required 默认 true;后者 JSR-250 提供、先按 name 再按 type、required 默认 false。JDK17 + Boot3 用 `jakarta.annotation.Resource`
4. **`@Component` / `@Service` / `@Repository` / `@Controller` 区别?** —— 都是 `@Component` 的特化;区别在语义清晰 + 只有 `@Repository` 会做持久层异常转换(`DataAccessException`)
5. **参数校验怎么做?** —— `@Valid` + `BindingResult` 收集 `FieldError`,按字段拼错误信息返回
6. **全局异常处理?** —— `@RestControllerAdvice` 统一捕获,避免每个 Controller 写 try-catch,保证返回格式一致
7. **`resultType` vs `resultMap`?** —— `resultType` 只能单表 + 靠驼峰自动映射;多表 join 的结果集必须用 `resultMap` 手工指定「列 → 嵌套对象属性」
8. **`<association>` vs `<collection>`?** —— 一对一用 `association`(属性是单对象),一对多用 `collection`(属性是 `List<T>`)。**`<collection>` 里的 `<id>` 是多行去重依据**,MyBatis 靠它判断多行是否属于同一个父对象
9. **N+1 查询问题?** —— 嵌套查询(`<collection select="...">`)会发 N+1 条 SQL。三种解法:① 一次 join 内嵌 resultMap(本项目用的)② 两次查询 + 内存拼接 ③ 延迟加载(只是把问题推后)
10. **`#{}` vs `${}`?** —— `#{}` 是 PreparedStatement 预编译占位符,防 SQL 注入;`${}` 是字符串直接拼接,**会注入**,只能用于白名单可控的表名/排序字段

## 踩坑记录(都是本项目真实踩过的)

> 这一节是本项目最有价值的部分 —— 全是"编译通过、测试通过,但结果是错的"的坑

1. **XML 的 `id` 必须和 Mapper 接口方法名一字不差**
   `deletById` / `upadteById` 少一个字母 → 启动报 `Invalid bound statement (not found)`

2. 🔥 **`resultMap` 的 `column` 不能带表前缀**
   写 `column="tc.customer_id"` → **静默返回 null**。因为 MyBatis 最终调 `rs.getObject("tc.customer_id")` 按**列标签**取值,而 MySQL 返回的标签是 `customer_id`
   **规则**:SQL 的 `select` 里**要**带表前缀(多表 join 消歧义),`resultMap` 的 `column` 里**不能**带

3. 🔥 **`<collection>` 里的 `<id>` 拼错 → 静默 null**
   写 `column="oreder_id"`(少个 r)→ 订单查得出来,但每条 `orderId` 都是 null

4. 🔥 **`<collection>` 的 `<id>` 换成 `<result>` → 对象重复**
   去重依据失效,3 行 SQL 会变成「3 个 Customer 各塞 1 个 Order」

5. **MyBatis 多参数必须加 `@Param`**
   不加只能拿到 `param1 / param2 / arg0`,XML 里用不了 `#{username}` 这种语义名

6. **多表 join 时 `select` 出来的列名必须唯一**
   `tc.customer_id` 和 `o.customer_id` 在结果集里都叫 `customer_id`,按标签取值会互相覆盖

7. **SQL 表别名不要用单引号** —— `'to'` 是 Oracle 风格,MySQL 容忍但 `ANSI_QUOTES` 模式下直接报错

8. **`@Transactional` 标在 Controller 上不生效** —— 它基于 AOP 代理,必须落在 Service

## 运行

```bash
mvn spring-boot:run
# 默认 http://localhost:8080
```

需要先建库 `db_demo_crud` 及 `sys_user` / `t_order` / `t_customer` 三张表。

```bash
mvn test
# OrderMapperTest / CustomerMapperTest 会真实连库执行 SQL
```

## 待办

- [ ] `SysUserServiceImpl` / `SysUserController` 移除无用 import
- [ ] `e.printStackTrace()` 换成 SLF4J 日志(`@Slf4j`)
- [ ] 查询无结果时返回成功 + 空列表,而不是 `fail("数据为空")`
- [ ] 接入 MyBatis-Plus 后给 Service 层加 `@Transactional`
- [ ] 密码改为 BCrypt 加密,不再硬编码默认值
