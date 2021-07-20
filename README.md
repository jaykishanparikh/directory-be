# Directory-be

Backend project for phone directory APIs built on java, springboot.

## Background

We are a Telecom operator. In our database, we are starting to store phone numbers associated to customers (1 customer:
N phone numbers), and we will provide interfaces to manage them. We need to provide the below capabilities:
• get all phone numbers • get all phone numbers of a single customer • activate a phone number

## Technology stake

- Main language - Java, Spring5
- ORM - Spring jpa
- Build - Gradle
- Database - H2 In memory, Postgres

See [API Documentation](/API.md)

## To build

Various gradle tasks are available to perform assemble build, jar, war etc.

## Development

On the local machine, H2 in memory database is used. However, it can easily be integrated with any other relational
databases. For example, Postgres

### Deploy code + database for local development

1. Install Docker for IntelliJ(IntelliJ 2020.2.3 provides stable support )
2. Install Docker for desktop(Mac or Windows) & Connect it within IntelliJ
3. Edit the docker-compose.yml configuration
   - add 'clean bootJar' gradle tasks of project
   - :white_check_mark: --build, force build images

   Run `<project>/docker-compose.yml` by clicking on green gutter-icon on 'services' key in file.

4. Open IntelliJ 'Services' view to find 2 containers - tomcat & postgres running
5. the docker-compose.yml runs tomcat in debug mode which allows hot reload

## Custom config

config parameters are to be stored in application.properties file placed under /resources.

## Api Specification

Specification for the APIs can be found at swagger ui through below location
[/api/app/directory/swagger-ui.html] (http://localhost:8080/api/app/directory/swagger-ui.html)

## Health check url

health check endpoints for directory apis
[/api/app/directory/manage/health] (http://localhost:8080/api/app/directory/manage/health)

=> returns

```
{
    "status": "UP",
    "components": {
        "db": {
            "status": "UP",
            "details": {
                "database": "H2",
                "validationQuery": "isValid()"
            }
        },
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 62725623808,
                "free": 56330145792,
                "threshold": 10485760,
                "exists": true
            }
        },
        "ping": {
            "status": "UP"
        }
    }
}
```

## Api testing through postman

Postman collection is part of this project and can be found under /postman directory. Import these environment files and
collection to test various apis.
