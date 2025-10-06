# 🏢 **YuOA 办公自动化系统设计文档**

**作者**：HuiFeiT
**项目名称**：YuOA（Your User Office Automation System）
**创建日期**：2025-10-06
**版本**：V1.0

---

## 一、项目简介

**YuOA** 是一款基于 **Java Spring Boot + MyBatis + MySQL + Vue3 + Element Plus** 技术栈构建的通用办公自动化系统。
系统目标是提供高效、可扩展的办公管理平台，实现用户管理、权限控制、流程审批、考勤统计、公告通知等核心功能。

---

## 二、系统总体架构

系统采用 **前后端分离 + RESTful API** 架构，整体结构如下：

```
用户浏览器
   │
   ▼
[前端：Vue3 + Element Plus]
   │ axios调用接口
   ▼
[后端：Spring Boot + MyBatis]
   │
   ▼
[数据库：MySQL]
```

---

## 三、技术栈说明

| 层级   | 技术                           | 说明             |
| ---- | ---------------------------- | -------------- |
| 前端   | Vue3 + Element Plus          | UI界面、路由管理、数据展示 |
| 接口通信 | Axios + JWT                  | 前后端通信及身份认证     |
| 后端框架 | Spring Boot 3.x              | 核心服务框架         |
| 数据访问 | MyBatis                      | 数据映射与持久层封装     |
| 数据库  | MySQL 8.x                    | 系统存储层          |
| 文档接口 | Swagger3 (springdoc-openapi) | 在线API文档        |
| 工具   | Lombok + PageHelper          | 简化开发与分页支持      |

---

## 四、模块设计

| 模块名称    | 主要功能说明                |
| ------- | --------------------- |
| 用户与权限管理 | 登录、注册、角色、菜单分配、Token验证 |
| 部门管理    | 部门层级结构、负责人设置          |
| 公告通知    | 公告发布、浏览、状态管理          |
| 审批流程    | 请假、报销等多级审批流转          |
| 考勤管理    | 打卡记录、统计分析             |
| 文件管理    | 上传、共享、版本控制            |
| 系统日志    | 用户操作记录、安全审计           |
| 通讯录     | 员工组织架构展示              |
| 日程管理    | 会议安排与提醒功能             |

---

## 五、数据库设计（YuOA）

### 1️⃣ 系统表结构概览

| 表名                    | 功能说明    |
| --------------------- | ------- |
| `sys_user`            | 用户信息    |
| `sys_role`            | 角色信息    |
| `sys_user_role`       | 用户角色关联  |
| `sys_menu`            | 菜单与权限管理 |
| `sys_role_menu`       | 角色菜单映射  |
| `sys_dept`            | 部门信息    |
| `sys_log`             | 操作日志    |
| `biz_leave`           | 请假申请    |
| `biz_approval_record` | 审批记录    |
| `biz_notice`          | 通知公告    |
| `biz_file`            | 文件管理    |

### 2️⃣ 示例表：`sys_user`

| 字段          | 类型           | 说明          |
| ----------- | ------------ | ----------- |
| id          | bigint       | 主键          |
| username    | varchar(50)  | 登录用户名       |
| password    | varchar(100) | 加密密码        |
| nickname    | varchar(50)  | 昵称          |
| dept_id     | bigint       | 所属部门        |
| phone       | varchar(20)  | 手机号         |
| email       | varchar(100) | 邮箱          |
| status      | tinyint      | 状态（1启用/0禁用） |
| create_time | datetime     | 创建时间        |
| update_time | datetime     | 更新时间        |

### 3️⃣ 业务表：`biz_leave`

| 字段          | 类型          | 说明            |
| ----------- | ----------- | ------------- |
| id          | bigint      | 主键            |
| user_id     | bigint      | 申请人           |
| type        | varchar(50) | 请假类型          |
| start_time  | datetime    | 开始时间          |
| end_time    | datetime    | 结束时间          |
| reason      | text        | 请假原因          |
| status      | varchar(20) | 状态（待审批/通过/驳回） |
| approver_id | bigint      | 审批人           |
| create_time | datetime    | 创建时间          |
| update_time | datetime    | 更新时间          |

---

## 六、后端设计（Spring Boot 部分）

**包结构规范：**

```
com.huifeit.yuoa
├── common          # 通用工具与响应封装
├── config          # 系统配置
├── controller      # 控制层接口
├── service         # 业务逻辑接口
├── service.impl    # 业务实现类
├── mapper          # MyBatis数据访问层
├── entity          # 实体类
└── YuOaApplication.java
```

**技术特性：**

* 使用 JWT 实现登录认证
* 支持 Spring Security 权限拦截
* 所有接口统一响应结构 `Result<T>`
* 自动生成 Swagger 文档
* 数据分页采用 PageHelper

---

## 七、前端设计（Vue3 + Element Plus）

**目录结构：**

```
src/
├── api/            # axios接口封装
├── components/     # 通用组件
├── router/         # 路由配置
├── store/          # Pinia状态管理
├── views/          # 页面视图
├── utils/          # 工具函数
└── App.vue
```

**核心页面：**

* `/login` 登录页
* `/dashboard` 工作台
* `/system/user` 用户管理
* `/system/role` 角色管理
* `/workflow/leave` 请假审批

---

## 八、开发阶段规划

| 阶段  | 内容         | 目标                |
| --- | ---------- | ----------------- |
| 阶段1 | 环境搭建、项目初始化 | 完成 YuOA 基础骨架运行    |
| 阶段2 | 用户登录与权限    | JWT登录、角色菜单权限控制    |
| 阶段3 | 核心业务模块     | 请假审批、公告管理         |
| 阶段4 | 文件与日志模块    | 文件上传、操作日志记录       |
| 阶段5 | 性能优化与部署    | Docker化部署、前后端打包上线 |

---

## 九、部署方案

* **后端部署**：Docker + Nginx + MySQL
* **前端构建**：`npm run build` 后静态资源放入 `/usr/share/nginx/html`
* **Nginx 反向代理配置**：

  ```nginx
  location /api/ {
      proxy_pass http://localhost:8080/;
  }
  ```

---

## 十、项目目标与愿景

YuOA 的目标是打造一个：

> 🔹 稳定
> 🔹 简洁
> 🔹 可扩展
> 🔹 适合团队与个人使用的办公系统框架。

后续版本将逐步引入：

* Flowable BPMN 工作流引擎
* WebSocket 实时消息通知
* 多租户结构支持