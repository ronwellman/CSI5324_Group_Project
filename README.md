[![CircleCI](https://dl.circleci.com/status-badge/img/gh/ronwellman/CSI5324_Group_Project/tree/main.svg?style=svg&circle-token=ed5ca9454aacbe5ad9f5cd07e65bdc64f63e5a9a)](https://dl.circleci.com/status-badge/redirect/gh/ronwellman/CSI5324_Group_Project/tree/main)

# Freelancer Service

*CSI 5324 Spring '23 trimester.*

## JWT
Authentication is via username and password. This produces a Java Web Token (JWT) that is used in future requests.  The authentication code to do this is a 95% copy of: [https://github.com/ali-bouali/spring-boot-3-jwt-security](https://github.com/ali-bouali/spring-boot-3-jwt-security) with very few modifications. 

## React Setup
```
npx create-react-app@5 app
```

```
cd app
npm i bootstrap@5 react-cookie@4 react-router-dom@6 reactstrap@9
```

```
npm install --save-dev @babel/preset-react
```
