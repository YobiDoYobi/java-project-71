plugins {
    id("java")
    application
    id("checkstyle")
    jacoco
}

application { mainClass.set("hexlet.code.App") }

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.5")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation ("org.assertj:assertj-core:3.25.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.3")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation ("commons-io:commons-io:2.15.1")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}