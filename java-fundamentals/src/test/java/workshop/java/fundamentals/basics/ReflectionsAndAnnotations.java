package workshop.java.fundamentals.basics;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.beans.JavaBean;
import java.lang.reflect.Field;

public class ReflectionsAndAnnotations {

    @Test(enabled = false)
    public void testName() throws NoSuchFieldException, IllegalAccessException {
        AnnotatedWithSomething service = new AnnotatedWithSomething("instance one");
        System.out.println(service.getServiceField());

        service.getClass();// gives same class as AnnotatedWithSomething.class;

        Class<? extends AnnotatedWithSomething> aClass = AnnotatedWithSomething.class;
        JavaBean annotation = aClass.getAnnotation(JavaBean.class);
        Assertions.assertThat(annotation.description()).isEqualTo("example bean");

        Field serviceField = aClass.getField("serviceField");
        serviceField.setAccessible(true);

        System.out.println(aClass.getSimpleName()
                + "." + serviceField.getName()
                + " = " + serviceField.get(service));

    }

    @JavaBean(description = "example bean")
    public static class AnnotatedWithSomething {

        private String serviceField = "init value";

        public AnnotatedWithSomething(String value) {
            this.serviceField = value;
        }

        public String getServiceField() {
            return serviceField;
        }
    }
}
