package exceptions.basic;

public class TryCatchSample {
    public String readDataFrom(Resource resource) {
        String ret;
        resource.open();
        try {
            ret = resource.read();
        } catch (RuntimeException e) {
            ret = "someDefaultValue";
        }
        resource.close();
        return ret;
    }
}
