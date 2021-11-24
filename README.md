# summit connect quarkus demo

## Bootstrapping Quarkus Applications with quarkus cli

You can create new quarkus apps by using the [quarkus cli tool](https://quarkus.io/guides/cli-tooling).

### Install Quarkus CLI
`$ curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio`


### Create new Quarkus app

The execution of 

`$ quarkus create app click.klaassen:summit-demo:1.0`

should give an output like 

```
Looking for the newly published extensions in registry.quarkus.io
-----------

applying codestarts...
📚  java
🔨  maven
📦  quarkus
📝  config-properties
🔧  dockerfiles
🔧  maven-wrapper
🚀  resteasy-codestart

-----------
[SUCCESS] ✅  quarkus project has been successfully generated in:
--> /Users/mklaasse/Dev_RedHat/summit-connect-quarkus-demo/summit-demo
-----------
Navigate into this directory and get started: quarkus dev
```

### Add Extensions

For our reactive crud application we want to use the following quarkus extensions:
* RESTEasy Reactive Jackson
* Hibernate Reactive with Panache
* Reactive PostgreSQL client

The execution of

`$ quarkus ext add quarkus-resteasy-reactive-jackson quarkus-hibernate-reactive-panache quarkus-reactive-pg-client`

should give an output like

```
[SUCCESS] ✅  Extension io.quarkus:quarkus-resteasy-reactive-jackson has been installed
[SUCCESS] ✅  Extension io.quarkus:quarkus-hibernate-reactive-panache has been installed
[SUCCESS] ✅  Extension io.quarkus:quarkus-reactive-pg-client has been installed
```

To avoid conflicts we have to remove the non-reactive RESTEasy extension which was added automatically while bootstrapping our application.

The command `$ quarkus ext rm quarkus-resteasy` should do it for us with following output:

```
[SUCCESS] ✅  Extension io.quarkus:quarkus-resteasy has been uninstalled
```

### Start Local Developer Environment

To start your local developer instance of quarkus app on your local machine use `$ quarkus dev`.
