package org.eclipse.osc.orchestrator;

import org.apache.karaf.minho.boot.Minho;
import org.apache.karaf.minho.boot.service.LifeCycleService;
import org.apache.karaf.minho.boot.service.ServiceRegistry;
import org.eclipse.osc.services.ocl.loader.OclLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OrchestratorServiceTest {

    @Test
    public void loadPluginTest() throws Exception {
        Minho karaf = Minho.builder().loader(() -> Stream.of(new LifeCycleService(), new OclLoader(), new OrchestratorService(), new PluginTest())).build().start();

        ServiceRegistry serviceRegistry = karaf.getServiceRegistry();
        OrchestratorService orchestratorService = serviceRegistry.get(OrchestratorService.class);

        Assertions.assertEquals(1, orchestratorService.getPlugins().size());
        Assertions.assertTrue(orchestratorService.getPlugins().get(0) instanceof PluginTest);

        PluginTest pluginTest = (PluginTest) orchestratorService.getPlugins().get(0);

        orchestratorService.registerManagedService("file:./target/test-classes/test.json");

        Assertions.assertEquals(1, orchestratorService.getManagedServices().size());
        List<String> managedServicesList = new ArrayList<>(orchestratorService.getManagedServices());
        Assertions.assertEquals("test-service", managedServicesList.get(0));

        Assertions.assertNotNull(pluginTest.getOcl());
        Assertions.assertEquals("test-service", pluginTest.getOcl().getName());

        orchestratorService.startManagedService("test-service");
        orchestratorService.stopManagedService("test-service");

        try {
            orchestratorService.startManagedService("43421");
            Assertions.fail("IllegalStateException expected before");
        } catch (Exception e) {
            // good
        }

        orchestratorService.unregisterManagedService("test-service");

        Assertions.assertEquals(0, orchestratorService.getManagedServices().size());

        Assertions.assertNull(pluginTest.getOcl());
    }

}
