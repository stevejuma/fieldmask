# Field Mask Core

[![Maven Central](https://img.shields.io/maven-central/v/ma.ju.fieldmask/fieldmask-core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22ma.ju.fieldmask%22%20AND%20a:%22fieldmask-core%22)

This is the core library that handles the field parsing and masking. You
can use this library standalone.

## Requirements and Downloads

Requirements:

* Java >= 1.8

## Install

Gradle:

```groovy
repositories {
    mavenCentral()
}

dependencies {
  // If using the core library only
  implementation 'ma.ju.fieldmask:fieldmask-core:1.0.5'
}
```

Maven:

```xml
<dependency>
  <groupId>ma.ju.fieldmask</groupId>
  <artifactId>fieldmask-core</artifactId>
  <version>1.0.5</version>
</dependency>
```

## Usage

Parsing a string for Paths and checking if a given path matches the
PathList

```kotlin
    /* To parse a field query into a path collection */
    val parser = FieldQueryParser()
    val paths  = parser.parse(query)
    
    /* Checking for matches for the given mask */
    val matcher = PathList.matcherFor("name,songs(title,albumId),albums/*")
    /* Matches all of these paths */
    matcher.matches("name")
    matcher.matches("songs/title")
    matcher.matches("songs/albumId")
    matcher.matches("albums/name")
    matcher.matches("albums/artist/name")
    
    /* Doesn't match any of these */
    matcher.matches("address/postCode")
    matcher.matches("unknown")
```

Creating a mask for the specified object

```kotlin
    val entity = Artist(id = 1001, name = "Avril Lavigne", songs = mutableListOf(
        Song(id = 2001, title = "Complicate", track = 3)
    ), albums = mutableListOf(
        Album(id = 3001, title = "Let Go", songCount = 12, year = 2002)
    ))
    val model = BeanMask.mask(entity, "name,songs(title)")
    /**
    * Returns: 
    * Map<String, *>{
    *   "name": "Avril Lavigne",
    *   "songs" [
    *     {"title": "Complicated",
    *   ]
    * }
    */
```

Copying data from one entity to another with a field mask

```kotlin
    val modelI = Artist(id = 1001, name = "Avril Lavigne")
    val modelII = Artist(id = 1002, name = "Taylor Swift")
    BeanMask.apply(modelI, modelII, "name")
    /**
    * Returns ModelII {
    *   name = "Avril Lavigne"
    * }
    */
```
