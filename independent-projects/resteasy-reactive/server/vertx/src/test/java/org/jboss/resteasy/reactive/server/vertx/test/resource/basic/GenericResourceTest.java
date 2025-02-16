package org.jboss.resteasy.reactive.server.vertx.test.resource.basic;

import java.util.function.Supplier;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.reactive.server.vertx.test.framework.ResteasyReactiveUnitTest;
import org.jboss.resteasy.reactive.server.vertx.test.resource.basic.resource.GenericResourceCrudResource;
import org.jboss.resteasy.reactive.server.vertx.test.resource.basic.resource.GenericResourceStudent;
import org.jboss.resteasy.reactive.server.vertx.test.resource.basic.resource.GenericResourceStudentCrudResource;
import org.jboss.resteasy.reactive.server.vertx.test.resource.basic.resource.GenericResourceStudentInterface;
import org.jboss.resteasy.reactive.server.vertx.test.resource.basic.resource.GenericResourceStudentReader;
import org.jboss.resteasy.reactive.server.vertx.test.resource.basic.resource.GenericResourceStudentWriter;
import org.jboss.resteasy.reactive.server.vertx.test.simple.PortProviderUtil;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * @tpSubChapter Resource
 * @tpChapter Integration tests
 * @tpTestCaseDetails Tests generic resource class
 * @tpSince RESTEasy 3.0.20
 */
@DisplayName("Generic Resource Test")
public class GenericResourceTest {

    private static WebTarget proxy;

    @BeforeAll
    public static void setup() {
        WebTarget target = ClientBuilder.newClient().target(generateURL(""));
        proxy = target.register(GenericResourceStudentReader.class).register(GenericResourceStudentWriter.class);
    }

    @RegisterExtension
    static ResteasyReactiveUnitTest testExtension = new ResteasyReactiveUnitTest()
            .setArchiveProducer(new Supplier<>() {
                @Override
                public JavaArchive get() {
                    JavaArchive war = ShrinkWrap.create(JavaArchive.class);
                    war.addClass(GenericResourceStudent.class);
                    war.addClass(PortProviderUtil.class);
                    war.addClass(GenericResourceStudentInterface.class);
                    war.addClass(GenericResourceCrudResource.class);
                    war.addClasses(GenericResourceStudentCrudResource.class, GenericResourceStudentReader.class,
                            GenericResourceStudentWriter.class);
                    return war;
                }
            });

    private static String generateURL(String path) {
        return PortProviderUtil.generateURL(path, GenericResourceTest.class.getSimpleName());
    }

    @Test
    @DisplayName("Test Get")
    @Disabled
    public void testGet() {
        //Assertions.assertTrue(proxy.get(1).getName().equals("Jozef Hartinger"));
    }

    @Test
    @DisplayName("Test Put")
    @Disabled
    public void testPut() {
        //proxy.put(2, new GenericResourceStudent("John Doe"));
        //Assertions.assertTrue(proxy.get(2).getName().equals("John Doe"));
    }
}
