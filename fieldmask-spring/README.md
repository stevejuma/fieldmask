# Field Mask Spring

[![Maven Central](https://img.shields.io/maven-central/v/ma.ju.fieldmask/fieldmask-spring.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22ma.ju.fieldmask%22%20AND%20a:%22fieldmask-spring%22)


Adds Field Masking to your API's in Spring Web / Spring MVC

## Requirements and Downloads

Requirements:

* Java >= 1.8
* Spring Boot / Spring MVC

## Install

Gradle:

```groovy
repositories {
    mavenCentral()
}

dependencies {
  // If using the core library only
  implementation 'ma.ju.fieldmask:fieldmask-spring:1.0.0'
}
```

Maven:

```xml
<dependency>
  <groupId>ma.ju.fieldmask</groupId>
  <artifactId>fieldmask-spring</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Usage

In your main application add `@EnableFieldMask` annotation to enable if
you are using spring boot

For every controller that you want to have partial responses. Add the
`@FieldMaskResponseBody` annotation. You can also add this to individual
methods within the controller. Method annotations take precedence over
class annotations. Alternatively you can set the `fieldmask.requireAnnotation`  
property to globally allow partial responses on all endpoints.


