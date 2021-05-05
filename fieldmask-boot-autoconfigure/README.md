# Field Mask Spring

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
  implementation 'ma.ju.fieldmask:fieldmask-boot-autoconfigure:1.0.0'
}
```

Maven:

```xml
<dependency>
  <groupId>ma.ju.fieldmask</groupId>
  <artifactId>fieldmask-boot-autoconfigure</artifactId>
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


