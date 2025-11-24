dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-web-services")

    // Jackson XML support
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

    // JAXB API for XML Binding
    implementation("jakarta.xml.bind:jakarta.xml.bind-api")
    implementation("org.glassfish.jaxb:jaxb-runtime")

    // XML Schema Validation
    implementation("jakarta.validation:jakarta.validation-api")

    // WSDL4J for SOAP Web Services
    implementation("wsdl4j:wsdl4j")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Development Tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}