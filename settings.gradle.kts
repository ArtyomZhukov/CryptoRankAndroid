dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CryptoRank"
include(":app")
include(":data")
include(":shared:core-ui")
include(":shared:core")
include(":shared:resources")

include(":feature:overview")
include(":feature:activity")
