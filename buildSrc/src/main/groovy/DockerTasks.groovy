import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.gradle.jvm.tasks.Jar

class DockerTasks {
    static IMAGE = 'newsreader-dwalczak'

    static void addTasks(Project project) {
        project.task('dockerRemoveImage', { group = 'docker'}).doLast {
            Utils.run("docker stop $IMAGE", [ignoreFailed: true])
            Utils.run("docker rm $IMAGE", [ignoreFailed: true])
            Utils.run("docker rmi $IMAGE", [ignoreFailed: true])
        }
        project.task('dockerBuildImage', { group = 'docker'; dependsOn = ['dockerRemoveImage', ':web-rs:build'] }).doLast {
            buildImage(project)
        }
        project.task('dockerBootRun', { group = 'docker'; dependsOn = ['dockerBuildImage'] }).doLast {
            Utils.run("docker run --name $IMAGE -p 8080:8080 -e newsapi_org_api_key=${Utils.getSystemProperty('newsapi_org_api_key')} $IMAGE")
        }
    }

    static void buildImage(Project project) {
        Jar jar = project.project(':web-rs').jar
        def dockerDir = jar.destinationDir
        FileUtils.copyFileToDirectory(new File(project.rootDir, 'src/main/resources/docker/Dockerfile'), dockerDir)
        Utils.filterFile(new File(dockerDir, 'Dockerfile'), '%jar%', jar.archiveName)
        Utils.run("docker build -t $IMAGE .", [workingDirectory: dockerDir])
    }

}
