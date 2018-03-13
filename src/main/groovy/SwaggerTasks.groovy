import io.swagger.codegen.DefaultGenerator
import io.swagger.codegen.config.CodegenConfigurator
import org.gradle.api.Project

class SwaggerTasks {
    static void addTasks(Project project) {
        project.task('generateServerStub', { group = 'generator' }) << {
            generateServerStub(project.rootDir.path, 'web-rs')
        }
    }

    static def generateServerStub(String rootDirPath, String projectDir) {
        def config = new CodegenConfigurator()
        config.setInputSpec("file:///$rootDirPath/src/main/resources/swagger-api.yaml")
        config.setOutputDir("$rootDirPath/$projectDir")
        config.setLang('spring')
        config.setAdditionalProperties([
                "groupId": 'com.dwalczak',
                "artifactId": 'newsreader',
                "basePackage": 'com.dwalczak.newsreader',
                "apiPackage": 'com.dwalczak.newsreader.api',
                "configPackage": "com.dwalczak.newsreader.configuration",
                "modelPackage": "com.dwalczak.newsreader.model",
                "hideGenerationTimestamp": true,
                "useTags": true,
                "sourceFolder": "src/main/java",
                "java8": true,
        ])
        new DefaultGenerator().opts(config.toClientOptInput()).generate()
    }
}
