# simpleXMLParser

[![codebeat badge](https://codebeat.co/badges/3211f6de-04cc-4b2a-940e-21a015181cb0)](https://codebeat.co/projects/github-com-vmalikov-simplexmlparser-master)

simpleXMLParser is a library written in Kotlin lang that can be used to convert XML into Java/Kotlin Objects representation. It can also be used to convert an XML string to an equivalent Java/Kotlin object.

The library requires to mark data class and each field which should be parsed with annotation @XMLName or @XMLAsArray.

### simpleXMLParser Goals

  * Provide simple parse() methods to convert input stream with XML or string into Java/Kotlin objects;
  * Provide a mechanism to parse elements with the same name into one array (could be usefull for parsing RSS feeds, to be exact ['item' elements](https://www.w3schools.com/xml/xml_rss.asp))
  * Allow custom representations for objects;
  * Support arbitrarily complex objects (with deep inheritance hierarchies and extensive use of generic types);
  * Simple installation by downloading via gradle;

### simpleXMLParser Documentation

#### simpleXMLParser API: 

The main class SimpleXmlParser.kt. It contains 2 methods in Companion object:
```kotlin
fun <T> parse(input: InputStream?, clazz: Class<T>): T?
```

```kotlin
fun <T> parse(input: String?, clazz: Class<T>): T?
```

### Usage

Kotlin code:
```kotlin 
var entry = SimpleXmlParser.parse(input, TestEntry::class.java!!)
```

Java code:
```java 
TestEntry entry = SimpleXmlParser.Companion.parse(input, TestEntry.class);
```

##### TestEntry class could be like:

Kotlin code:

Can be used with ```data class``` (in current version constructor params should have default value. The library needs default constructor in class)
```kotlin
@XmlName(name = "item")
data class TestEntry(@XmlName(name = "title") var title: String = "",
    @XmlName(names = *arrayOf("content:encoded", "description")) var description: String = "")
```
Also can be used with regular classes:
```kotlin
@XmlName(name = "item")
class TestEntry {
    @XmlName(name = "title") var title: String? = null
    @XmlName(names = *arrayOf("content:encoded", "description")) var description: String? = null
}
```

Java code:
```java
@XmlName(name = "channel")
public class TestEntry {
    @XmlName(name = "title")
    public String title;
    @XmlAsArray(name = "item")
    public ArrayList<FeedEntryWithAnnotation> items = new ArrayList<>();
}
```

### Download 

Note that Gradle is the only supported build configuration for simpleXMLParser. To add the library to your app's build.gradle file.
```
buildscript {
    repositories {
        jcenter()
    }
}

dependencies {
    compile 'com.justforfun:simplexmlparser:1.0.0'
}
```

Please use the [SimpleXMLParser issue tracker](https://github.com/vmalikov/simplexmlparser/issues) to discuss anything related to library.

### License

SimpleXMLParser is released under the Apache 2.0 license.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
