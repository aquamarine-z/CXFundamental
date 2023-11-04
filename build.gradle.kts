import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val kotlinVersion = "1.9.20-RC2"
buildscript {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public/") }
        maven { url = uri("https://maven.aliyun.com/repository/spring/") }
        maven { url = uri("https://maven.aliyun.com/repository/google/") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin/") }
        maven { url = uri("https://maven.aliyun.com/repository/spring-plugin/") }
        maven { url = uri("https://maven.aliyun.com/repository/grails-core/") }
        maven { url = uri("https://maven.aliyun.com/repository/apache-snapshots/") }
        mavenLocal()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.5.0")


    }
}
plugins {
    id("java")
    //id("com.github.johnrengelman.shadow") version "5.0.0"
    id("org.jetbrains.dokka") version "1.9.10"
    kotlin("jvm") version "1.9.20-RC2" //String' can't be called in this context by implicit receiver. Use the explicit one if necessary
}
apply {
    plugin("java")
    plugin("idea")
}

dependencies {
    implementation("org.jetbrains.dokka", "dokka-gradle-plugin", "1.5.0")
    //implementation("org.jetbrains.kotlinx,kotlinx-coroutines-core:1.5.1")
    //implementation("org.jetbrains.dokka", "dokka-gradle-plugin", "1.4.10.2")
    //implementation("org.jetbrains.dokka", "dokka-core", "1.4.10.2")
    //implementation("org.jetbrains.dokka", "dokka-base", "1.4.10.2")
    //compile ("org.jetbrains.kotlin:kotlin-compiler:$kotlinVersion")
    //compile ("org.jetbrains.kotlin:kotlin-scripting-compiler:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("commons-cli:commons-cli:20040117.000000")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation(files("libs/spigot-1.12.2.jar"))
    //dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.5.0")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.5.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.20-RC2")
    implementation("io.netty:netty-all:4.1.47.Final")

    //compile ("org.jetbrains.kotlin", "kotlin-script-runtime", "$kotlinVersion")
    //    //compile ("org.jetbrains.kotlin", "kotlin-script-util", "$kotlinVersion")
    //    //compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    //    //compile ("io.netty:netty-all:4.1.47.Final")
    implementation("junit", "junit", "4.12")
}



version = "1.6.4-Unstable"
repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}
tasks.compileJava {

}
java {
    targetCompatibility = JavaVersion.VERSION_1_8
}
kotlin {
    compilerOptions {
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_9)
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}
kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}
/*val buildRuntimeJar = task("buildRuntimeJar", type = Jar::class) {
    baseName = "${project.name}-Runtime"
    from(configurations.implementation.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
    exclude ("libs/spigot-1.12.2.jar")
    this.destinationDir=File("./runtime")
}*/

val buildApiJar = task("buildApiJar", type = Jar::class) {
    dependsOn("compileKotlin") // 依赖于Kotlin编译任务，确保在此任务执行之前先编译Kotlin代码
    from(sourceSets.getByName("main").output) // 添加编译输出目录到JAR中
    exclude("**/dependencies/**") // 排除依赖目录下的文件
    includeEmptyDirs = false // 不包含空目录
    baseName = "CXFundamental-Api"
    destinationDir = File("api")
}

