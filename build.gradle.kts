import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("com.google.cloud.tools.jib") version "1.8.0"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
}

group = "cf.moviebot"
java.sourceCompatibility = JavaVersion.VERSION_1_8

allprojects {
	repositories {
		mavenCentral()
	}
}

subprojects {
	apply {
		plugin("kotlin-spring")
		plugin("io.spring.dependency-management")
		plugin("org.jetbrains.kotlin.jvm")
		plugin("org.jetbrains.kotlin.plugin.spring")
		plugin("com.google.cloud.tools.jib")
	}

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.3.3")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		testImplementation("org.springframework.boot:spring-boot-starter-test") {
			exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

	jib {
		container.useCurrentTimestamp = true
		from.image = "openjdk:8-alpine"
	}
}

configure(subprojects - project(":shared")) {
	apply {
		plugin("org.springframework.boot")
	}
	dependencies {
		compile(project(":shared"))
	}
}


tasks.withType<Wrapper> {
	gradleVersion = "5.5"
}