import org.apache.commons.lang3.StringUtils
import org.gradle.api.GradleException

class Utils {

    static def run(String cmd, Map params = [:]) {
        def t1 = System.currentTimeMillis()
        BufferedOutputStream out = new BufferedOutputStream(System.out)
        out << "# $cmd\n"
        def process = params.workingDirectory == null ? cmd.execute() : cmd.execute([], params.workingDirectory)
        process.waitForProcessOutput(out, out)
        if (process.exitValue() != 0 && !params.ignoreFailed) {
            throw new GradleException("Failed!")
        }
        out << " ... done in ${System.currentTimeMillis() - t1} ms\n"
    }

    static String getSystemProperty(String property) {
        String value = System.getProperty(property)
        if (StringUtils.isBlank(value)) {
            throw new GradleException("System property $property is not set")
        }
        value
    }

    static void filterFile(File file, String toSearch, String replacement) {
        file.write(file.text.replaceAll(toSearch, replacement))
    }

}
