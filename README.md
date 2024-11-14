kubectl delete deployments --all
kubectl delete deployments --all -n kube-system
kubectl delete ingress --all
minikube addons disable ingress
kubectl get deployments
kubectl get ingress
helm uninstall api-b-helm

Deploy service B
=====================================================================
minikube start
minikube addons enable ingress

cd under resource-b folder where Dockerfile file reside.
eval $(minikube docker-env)
docker build -t resource-b-image:latest .

cd under resource-b/api-b-helm folder where values.yaml reside.
helm install api-b-helm .
---------------------------------------------------------------------
for testing purpose
kubectl port-forward svc/api-b-helm 8081:8080
Verify
http://localhost:8081
---------------------------------------------------------------------
minikube tunnel

sudo vim /etc/hosts
echo "127.0.0.1 resource-b-domain.local" | sudo tee -a /etc/hosts

Then Test 
http://resource-b-domain.local
---------------------------------------------------------------------

..

Deploy service A
=====================================================================
cd under resource-a folder where Dockerfile reside.
eval $(minikube docker-env)
docker build -t resource-a-image:latest .

cd under resource-a/api-a-helm folder where values.yaml reside.
helm install api-a-helm .
---------------------------------------------------------------------
for testing purpose
kubectl port-forward svc/api-a-helm 8082:8080
Verify
http://localhost:8082/hello?to=api-b-helm
---------------------------------------------------------------------
sudo vim /etc/hosts
echo "127.0.0.1 resource-a-domain.local" | sudo tee -a /etc/hosts

Then Test 
http://resource-a-domain.local/hello?to=api-b-helm
---------------------------------------------------------------------

UI
helm plugin install https://github.com/komodorio/helm-dashboard.git
helm dashboard --port 8081


=====================================================================
extras commands
=====================================================================
minikube tunnel
helm install my-nginx ingress-nginx/ingress-nginx --set controller.publishService.enabled=true

echo "$(minikube ip) a.local" | sudo tee -a /etc/hosts
helm install api-b-helm .
helm uninstall api-b-helm




