dependencies {
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-actuator")
    testImplementation("org.springframework.amqp:spring-rabbit-test")
}