[![CircleCI](https://dl.circleci.com/status-badge/img/gh/ronwellman/CSI5324_Group_Project/tree/main.svg?style=svg&circle-token=ed5ca9454aacbe5ad9f5cd07e65bdc64f63e5a9a)](https://dl.circleci.com/status-badge/redirect/gh/ronwellman/CSI5324_Group_Project/tree/main)

# Freelancer Service

*CSI 5324 Spring '23 trimester.*

## JWT

Authentication is via username and password. This produces a Java Web Token (JWT) that is used in future requests.
The authentication code to do this is a copy
of: [https://github.com/ali-bouali/spring-boot-3-jwt-security](https://github.com/ali-bouali/spring-boot-3-jwt-security)
with only a handful modifications.

## React Setup

```bash
npx create-react-app@5 app
```

```bash
cd app
npm i bootstrap@5 react-cookie@4 react-router-dom@6 reactstrap@9
```

```bash
npm install --save-dev @babel/preset-react
```

## Postgres

A docker-compose file exists for launching postgres.

```bash
docker-compose up  
```

### USERNAME / PASSWORD

```bash
cat .env
POSTGRESSUSER=SOMEUSERNAME
POSTGRESSPASSWORD=SOMEPASSWORD
```

You can verify they are being picked up via:

```bash
docker-compose config
```

### DB Setup

Open browser to [http://localhost:5050](http://localhost:5050) to set an admin password and create the database.

The database should be named: **task_hiring**
