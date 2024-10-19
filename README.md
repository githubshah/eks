kubectl delete deployments --all
kubectl delete deployments --all -n kube-system
kubectl delete ingress --all
minikube addons disable ingress
kubectl get deployments
kubectl get ingress
helm uninstall api-b-helm

Deploy service B
minikube start
minikube addons enable ingress

cd under resource-b folder where Dockerfile file reside.
eval $(minikube docker-env)
docker build -t resource-b-image:latest .

cd under resource-b/api-B-helm folder where values.yaml reside.
helm install api-b-helm .

for testing purpose
kubectl port-forward svc/<service-name> 8081:8080
Verify
http://localhost:8081


minikube tunnel

sudo vim /etc/hosts
echo "127.0.0.1 resource-b-domain.local" | sudo tee -a /etc/hosts
 


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


minikube tunnel
helm install my-nginx ingress-nginx/ingress-nginx --set controller.publishService.enabled=true

echo "$(minikube ip) a.local" | sudo tee -a /etc/hosts


helm install api-b-helm .
helm uninstall api-b-helm




