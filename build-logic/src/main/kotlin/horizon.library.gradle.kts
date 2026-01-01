plugins {
    `maven-publish`
}

tasks {
    publishing {
        publications {
            all {
                if (this !is MavenPublication) return@all

                pom {
                    name.set("horizon")
                    description.set("My silly attempt of making a reactive js lib.")
                    url.set("https://github.com/TriumphTeam/horizon")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://www.opensource.org/licenses/mit-license.php")
                        }
                    }

                    developers {
                        developer {
                            id.set("matt")
                            name.set("Mateus Moreira")
                        }
                    }

                    // Change later
                    scm {
                        connection.set("scm:git:git://github.com/TriumphTeam/horizon.git")
                        developerConnection.set("scm:git:ssh://github.com:TriumphTeam/horizon.git")
                        url.set("http://github.com/TriumphTeam/horizon")
                    }
                }
            }
        }

        repositories {
            maven {
                url = if (version.toString().endsWith("-SNAPSHOT")) {
                    uri("https://repo.triumphteam.dev/snapshots/")
                } else {
                    uri("https://repo.triumphteam.dev/releases/")
                }

                credentials {
                    username = providers.gradleProperty("triumph.repo.user").orNull ?: System.getenv("GITHUB_ACTOR")
                    password = providers.gradleProperty("triumph.repo.token").orNull ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}
