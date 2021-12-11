# Spring boot Mybatis plus Dynamic databases

## 自動切換 databases資料源.


##YAML

	spring:
	  dynamic:
	    datasource:
	      master:
	        jdbc-url: jdbc:mysql://localhost:3306/project?useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true&useSSL=false
	        username: root
	        password: 123456
	        driver-class-name: com.mysql.jdbc.Driver
	        connection-init-sqls: set names utf8mb4
	        connectionProperties: druid.stat.slowSqlMillis=200;druid.stat.logSlowSql=true;config.decrypt=false
	        filters: stat
	        maxActive: 20
	        initialSize: 1
	        maxWait: 60000
	        minIdle: 1
	        timeBetweenEvictionRunsMillis: 60000
	        minEvictableIdleTimeMillis: 300000
	        validationQuery: select 'x'
	        testWhileIdle: true
	        testOnBorrow: false
	        testOnReturn: false
	        poolPreparedStatements: true
	        maxOpenPreparedStatements: 20
	      slave:
	        jdbc-url: jdbc:mysql://localhost:3306/project?useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true&useSSL=false
	        username: root
	        password: 123456
	        driver-class-name: com.mysql.jdbc.Driver
	        connection-init-sqls: set names utf8mb4
	        connectionProperties: druid.stat.slowSqlMillis=200;druid.stat.logSlowSql=true;config.decrypt=false
	        filters: stat
	        maxActive: 20
	        initialSize: 1
	        maxWait: 60000
	        minIdle: 1
	        timeBetweenEvictionRunsMillis: 60000
	        minEvictableIdleTimeMillis: 300000
	        validationQuery: select 'x'
	        testWhileIdle: true
	        testOnBorrow: false
	        testOnReturn: false
	        poolPreparedStatements: true
	        maxOpenPreparedStatements: 20

	mybatis-plus:
	  mapper-locations: classpath:mapper/*.xml
	  typeAliasesPackage: com.myProject.xxx.entity
	  global-config:
	    id-type: 3
	    field-strategy: 2
	    db-column-underline: true
	  configuration:
	    map-underscore-to-camel-case: true
	    cache-enabled: false
	    jdbc-type-for-null: 'null'