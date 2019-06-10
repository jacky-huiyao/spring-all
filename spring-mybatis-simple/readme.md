### simple demo for mybatis configuration

#### 步骤

1. 引入jar包 
    
    ```groovy
    // https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter
    compile group: 'org.mybatis.spring.boot', name: 'mybatis-spring-boot-starter', version: '2.0.1'

2. 编写mybatis方法

    UserDao.java
    ```java
       public interface UserDao {
       
           void save(User user);
       
           User findById(Integer id);
       
           List<User> findAll();
       }
    ```
    新建UserDao.xml,位于 resources/mapper/文件下
    ```xml
       <?xml version="1.0" encoding="UTF-8" ?>
       <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
       
       <mapper namespace="com.hy.mybatis.dao.UserDao">
       
           <resultMap id="userMap" type="com.hy.mybatis.domain.User">
               <id property="id" column="id" javaType="int" jdbcType="INTEGER"/>
               <result property="name" column="name" javaType="string" jdbcType="VARCHAR"/>
               <result property="salary" column="salary" javaType="BIGDECIMAL" jdbcType="DECIMAL"/>
               <result property="birthDate" column="birth_date" javaType="java.time.LocalDate" jdbcType="DATE"/>
               <result property="lastLoginTime" column="last_login_time" javaType="java.time.LocalDateTime" jdbcType="TIMESTAMP"/>
           </resultMap>
       
           <insert id="save" parameterType="com.hy.mybatis.domain.User">
               insert into user(id,name,birth_date,salary,status,last_login_time)
               values(#{id},#{name},#{birthDate},#{salary}.#{status},#{lastLoginTime})
           </insert>
       
           <select id="findById" resultMap="userMap">
               select * from
               user
               where id = #{id}
           </select>
           <select id="findAll" resultMap="userMap">
               select * from user
           </select>
       
       </mapper>
    ```
3. 配置
    
    ① 扫描xml文件
    
      在application.yml文件添加
      
        mybatis:
          mapper-locations: mapper/*.xml    # 扫描mapper目录下的所有xml文件
          type-aliases-package: com.hy.mybatis.domain  # 扫描domain下的所有类作为别名
    
    ② 注册mapper方法为spring bean
    有两种方法
    
    第一种是使用@MapperScan统一配置
    
        @Configuration
        @MapperScan(basePackages = "com.hy.mybatis.dao")  //注册basePackages下所有接口为bean
        public class MybatisConfig {
            ...
        }
        
    第二种是用@Mapper注解，需要加到每一个mapper类下,例如
        
        @Mapper
        public interface UserDao {
            ...
        }
 
#### 启动项目

用内置的H2的数据库，可以访问 http://localhost:8080/h2-console 操作数据库
    
    url: jdbc:h2:mem:test
    username: root
    password: 123456

1. start Application类
2. 访问 http://localhost:8080/user/list


    [{"id":1,"name":"张三","birthDate":[2019,6,10],"salary":10000.00,"status":"NORMAL","lastLoginTime":[1970,1,1,17,52,57]}]

