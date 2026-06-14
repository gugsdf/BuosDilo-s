# 🚀 GUIA DE DEPLOY NO RENDER
## Pre-requisitos
✅ Conta no Render (https://render.com/)
✅ Repositorio GitHub com o codigo
✅ Banco PostgreSQL no Aiven (ja configurado)
## Passo 1: Fazer Build Local
\\\ash
mvn clean package
\\\
## Passo 2: Fazer Commit e Push no GitHub
\\\ash
git add .
git commit -m "Preparacao para deploy no Render"
git push origin main
\\\
## Passo 3: Criar Web Service no Render
1. Acesse https://dashboard.render.com/
2. Clique em "New +" > "Web Service"
3. Selecione seu repositorio GitHub
4. Configure:
   - Name: ecommerce-api
   - Runtime: Java
   - Build Command: mvn clean package -DskipTests
   - Start Command: O Procfile ja esta configurado
   - Instance Type: Free (ou paga se preferir)
## Passo 4: Configurar Variaveis de Ambiente
No dashboard do Render, va em Environment e adicione as variaveis do arquivo .env.example
## Passo 5: Deploy
Clique em "Create Web Service" e aguarde o deploy completar.
## Passo 6: Testar a API
Apos o deploy, copie a URL do seu servico (ex: https://ecommerce-api.onrender.com)
Teste com:
\\\ash
GET https://ecommerce-api.onrender.com/api/produtos
\\\
## Arquivos Configurados
✅ Procfile - Define como rodar a aplicacao
✅ system.properties - Especifica Java 21
✅ application-prod.properties - Configuracoes de producao
✅ .env.example - Template de variaveis de ambiente
## IMPORTANTE
- Nao faca commit do arquivo application.properties (ja esta no .gitignore)
- A porta e gerenciada automaticamente pelo Render via variavel PORT
- O banco de dados usa a mesma URL de desenvolvimento
- NUNCA faça commit de senhas no Git! Use variaveis de ambiente
## Links Uteis
- Render Dashboard: https://dashboard.render.com/
- Documentacao Render: https://render.com/docs
- Aiven Console: https://console.aiven.io/
