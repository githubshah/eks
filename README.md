minikube start

Deploy Resporce B

cd under resource-b folder where Dockerfile reside.
eval $(minikube docker-env)
docker build -t resource-b-image:latest .

cd under resource-b/api-B-helm folder where values.yaml reside.
helm install deployment-b .

for testing purpose
kubectl port-forward svc/deployment-b-api-b-helm 8081:8080
Verify
http://localhost:8081

..

Deploy Resporce A

cd under resource-a folder where Dockerfile reside.
eval $(minikube docker-env)
docker build -t resource-a-image:latest .

cd under resource-a/api-a-helm folder where values.yaml reside.
helm install deployment-a .

for testing purpose
kubectl port-forward svc/deployment-a-api-a-helm 8082:8080
Verify
http://localhost:8082/hello?to=deployment-b-api-b-helm

UI
helm plugin install https://github.com/komodorio/helm-dashboard.git
helm dashboard --port 8081



Once forward testing done then
minikube addons enable ingress
minikube tunnel
helm install my-nginx ingress-nginx/ingress-nginx --set controller.publishService.enabled=true

echo "$(minikube ip) a.local" | sudo tee -a /etc/hosts




