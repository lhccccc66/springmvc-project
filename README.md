# 用户管理模块(user-management)

> 基于 Spring Boot 3.x + SpringMVC + SpringIOC 的后台用户管理模块
> 大二上课程项目,是后续「进销存项目」的技术底座

## 技术栈

| 技术 | 版本 | 用途 |
|---|---|---|
| JDK | 17 | |
| Spring Boot | 3.x | 框架 |
| SpringMVC | 内置 | Web 层,注解开发 REST 接口 |
| Spring IOC | 内置 | 依赖注入 + 三层架构 |
| MySQL | 8.x | 持久层(规划中,day06 MyBatis 接入) |
| MyBatis-Plus | 规划中 | ORM,替代手写 JDBC |
| Maven | 3.x | 依赖管理 |
| Lombok | | `@Data` 自动生成 getter/setter |
| Validation | jakarta.validation | `@Valid` + JSR-380 参数校验 |
| Thymeleaf | | 页面模板(forward 跳转) |

## 功能清单

- [x] 用户列表条件查询(用户名 / 手机号 / 状态 三个可选条件)
- [x] 用户详情查询(`/user/{id}`)
- [x] 新增用户(`@Valid` + `BindingResult` 参数校验)
- [x] 修改用户
- [x] 删除用户
- [x] 登录 / 注销(Session 维持登录态)
- [x] 登录拦截器(未登录拦截,静态资源放行)
- [x] 统一响应格式 `Result`
- [x] 全局异常处理(`@RestControllerAdvice` + `@ExceptionHandler`)
- [x] 参数校验注解(`@NotBlank` / `@Size` / `@Pattern` / `@Min` / `@Max`)
- [ ] 接入 MySQL + MyBatis-Plus(持久层)
- [ ] 密码加密存储(BCrypt)

## 三层架构

```
Controller(接参 / 调 Service / 返 Result)
    ↓
Service(业务逻辑 / 事务边界)
    ↓
DAO(数据访问)
```

```
com.atguigu.springmvc
├── controller/      UserController、LoginController、ResponseController...
├── service/         SysUserService + Impl、LoginService + Impl
├── dao/             SysUserDao + Impl、LoginDao + Impl
├── entities/        SysUser
├── dto/             SysUserLoginDto
├── interceptor/     LoginProtectInterceptor
├── config/          InterceptorConfig、LoginInterceptorConfig
├── exception/       GlobalExceptionHandler
└── util/            Result(统一响应)、SysUserDataUtil(临时内存数据)
```

**分层原则**:
- Controller 不碰数据,只做协议转换
- Service 不出现 `HttpServletRequest`,业务可脱离 Web 复用
- DAO 不做业务判断,只存取数据
- **事务必须标在 Service 层**(`@Transactional` 基于 AOP 代理,标在 Controller 上不生效)

## 亮点(面试可讲)

1. **为什么要分层?** —— 职责分离、解耦复用、便于单元测试;且 Spring 事务必须落在 Service 层
2. **拦截器和 Filter 的区别?** —— Filter 属 Servlet 规范管不了 Controller 方法;Interceptor 属 SpringMVC 规范,能拿到 HandlerMethod。多个拦截器按洋葱模型执行(preHandle 正序 / postHandle 逆序)
3. **`@Autowired` vs `@Resource`?** —— 前者 Spring 提供、按类型、required 默认 true;后者 JSR-250 提供、先按 name 再按 type、required 默认 false。JDK17 + Boot3 用 `jakarta.annotation.Resource`
4. **`@Component` / `@Service` / `@Repository` / `@Controller` 区别?** —— 都是 `@Component` 的特化;区别在语义清晰 + 只有 `@Repository` 会做持久层异常转换(`DataAccessException`)
5. **参数校验怎么做?** —— `@Valid` + `BindingResult` 收集 `FieldError`,按字段拼错误信息返回
6. **全局异常处理?** —— `@RestControllerAdvice` 统一捕获,避免每个 Controller 写 try-catch,保证返回格式一致

## 运行

```bash
mvn spring-boot:run
# 默认 http://localhost:8080
```

## 待办

- [ ] 清理 `SysUserController.java旧1` 等备份文件
- [ ] `SysUserServiceImpl` / `SysUserController` 移除无用 import
- [ ] `e.printStackTrace()` 换成 SLF4J 日志(`@Slf4j`)
- [ ] 查询无结果时返回成功 + 空列表,而不是 `fail("数据为空")`
- [ ] 接入 MySQL 后,Service 层加 `@Transactional`
- [ ] 密码改为 BCrypt 加密,不再硬编码默认值
