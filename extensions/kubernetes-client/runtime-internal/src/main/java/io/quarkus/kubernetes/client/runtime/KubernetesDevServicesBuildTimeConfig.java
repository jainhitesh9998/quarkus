package io.quarkus.kubernetes.client.runtime;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class KubernetesDevServicesBuildTimeConfig {

    /**
     * If Dev Services for Kubernetes should be used. (default to true)
     *
     * If this is true and kubernetes client is not configured then a kubernetes cluster
     * will be started and will be used.
     */
    @ConfigItem(defaultValue = "true")
    public boolean enabled;

    /**
     * The kubernetes api server version to use.
     *
     * If not set, Dev Services for Kubernetes will use the latest supported version of the given flavor.
     * see https://github.com/dajudge/kindcontainer/blob/master/k8s-versions.json
     */
    @ConfigItem
    public Optional<String> apiVersion;

    /**
     * The flavor to use (kind, k3s or api-only). Default to api-only.
     */
    @ConfigItem(defaultValue = "API_ONLY")
    public Flavor flavor;

    /**
     * By default, if a kubeconfig is found, Dev Services for Kubernetes will not start.
     * Set this to true to override the kubeconfig config.
     */
    @ConfigItem(defaultValue = "false")
    public boolean overrideKubeconfig;

    /**
     * Indicates if the Kubernetes cluster managed by Quarkus Dev Services is shared.
     * When shared, Quarkus looks for running containers using label-based service discovery.
     * If a matching container is found, it is used, and so a second one is not started.
     * Otherwise, Dev Services for Kubernetes starts a new container.
     * <p>
     * The discovery uses the {@code quarkus-dev-service-kubernetes} label.
     * The value is configured using the {@code service-name} property.
     * <p>
     * Container sharing is only used in dev mode.
     */
    @ConfigItem(defaultValue = "true")
    public boolean shared;

    /**
     * The value of the {@code quarkus-dev-service-kubernetes} label attached to the started container.
     * This property is used when {@code shared} is set to {@code true}.
     * In this case, before starting a container, Dev Services for Kubernetes looks for a container with the
     * {@code quarkus-dev-service-kubernetes} label
     * set to the configured value. If found, it will use this container instead of starting a new one. Otherwise, it
     * starts a new container with the {@code quarkus-dev-service-kubernetes} label set to the specified value.
     * <p>
     * This property is used when you need multiple shared Kubernetes clusters.
     */
    @ConfigItem(defaultValue = "kubernetes")
    public String serviceName;

    public static enum Flavor {
        /**
         * kind (needs priviledge docker)
         */
        KIND,
        /**
         * k3s (needs priviledge docker)
         */
        K3S,
        /**
         * api only
         */
        API_ONLY;
    }
}
