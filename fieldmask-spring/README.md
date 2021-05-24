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
  implementation 'ma.ju.fieldmask:fieldmask-spring:1.0.8'
}
```

Maven:

```xml
<dependency>
  <groupId>ma.ju.fieldmask</groupId>
  <artifactId>fieldmask-spring</artifactId>
  <version>1.0.8</version>
</dependency>
```

## Usage

In your main application add the `@EnableFieldMask` annotation to enable if
you are using spring boot

For every controller that you want to have partial responses. Add the
`@FieldMaskResponseBody` annotation. You can also add this to individual
methods within the controller. Method annotations take precedence over
class annotations. Alternatively you can set the `fieldmask.requireAnnotation`  
property to globally allow partial responses on all endpoints.

## Controllers
```kotlin
/**
* You need to enable the configuration with the `@EnableFieldMask` annotation
* to get started.
*/
@SpringBootApplication
@EnableFieldMask
open class Application 

/**
 * We can add the @FieldMaskResponseBody annotation either to the class
 * or the individual controller methods that we have
 */
@RestController
@FieldMaskResponseBody
class DemoController {
    /**
    * When using partial responses you need to return either `Any` 
    * or `ResponseEntity<*>` as the response is dynamic
    */
    @RequestMapping("/demo/v1", method = [RequestMethod.GET])
    fun listDemos(): ResponseEntity<*> {
        return ResponseEntity.ok(Demo())
    }
   
    /**
    * We disable partial responses for this endpoint. This annotation
    * takes precedence over the class level one
    */ 
    @RequestMapping("/demo/v1/{id}", method = [RequestMethod.GET])
    @FieldMaskResponseBody(false)
    fun getDemo(id: Long): Demo {
        return Demo(id = id)
    }
}
```

## Configuration

```yaml
fieldmask:
  # Whether we need to check for the `@FieldMaskResponseBody` 
  # annotation on a method or class before we apply the field masks.
  requireAnnotation: true
  # Whether to validate specified fields in the request 
  validate: true
  # Bean path options 
  path: 
    # Whether to include private properties when checking for valid paths
    includePrivate: false
  # The property to look for in the request for the field masks
  fieldsProperty: "fields"
  # The default separator when display path fragments
  separator: /
```


