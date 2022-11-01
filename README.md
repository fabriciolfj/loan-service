# loan-service Project

## Funcionalidades
- modalidade de emprestimos
  - aposentao/pensionista
  - privado
  - consignado
  
- sugestão de emprestimos, com base no 30% do valor do salário + juros x prazo máximo
- aprovação ou negação do emprestimo
- geração do contrato, caso seja concluido com sucesso
- envio do contrato atravez de eventos, para o servico de comissionamento
- analise de risco, com base:
   - numero de emprestimos anteriores
   - score
   - tempo de conta
   
## Tecnologias utilizadas
 - java17
 - mutiny
 - kafka
 - minikube/k8s
 - istio
 - argocd
