# ⚠️ CONFIGURAÇÃO IMPORTANTE - LEIA ANTES DE USAR

## 🔑 Passo 1: Configure sua API Key

Antes de usar a aplicação, você **DEVE** configurar sua chave de API do Riot Games:

1. **Obtenha sua API Key:**
   - Acesse: https://developer.riotgames.com/
   - Faça login com sua conta Riot Games
   - Copie sua Development API Key (válida por 24 horas)

2. **Configure a aplicação:**
   - Abra o arquivo: `src/main/resources/application.properties`
   - Substitua `RGAPI-YOUR-API-KEY-HERE` pela sua chave real:
   
   ```properties
   riot.api.key=RGAPI-1234567890abcdef-sua-chave-aqui
   ```

3. **Reinicie a aplicação** se ela já estiver rodando

## 🚀 Passo 2: Execute a Aplicação

```bash
mvn spring-boot:run
```

Aguarde até ver a mensagem:
```
Started LolStatsApplication in X.XXX seconds
```

## 🌐 Passo 3: Acesse a Interface

Abra seu navegador em:
```
http://localhost:8080
```

## 🎮 Passo 4: Busque um Jogador

### Exemplos de Jogadores Famosos:

**Brasil:**
- Nome: `brTT` | Tag: `BR1`
- Nome: `Robo` | Tag: `BR1`

**Coreia:**
- Nome: `Hide on bush` | Tag: `KR1` (Faker)

**Europa:**
- Nome: `Caps` | Tag: `EUW`

**América do Norte:**
- Nome: `Doublelift` | Tag: `NA1`

### Como Buscar:

1. Digite o nome do jogador (sem o #)
2. Digite a tag (BR1, KR1, EUW, NA1, etc)
3. Escolha quantas partidas analisar (padrão: 20, máximo: 100)
4. Clique em **Buscar**

⏱️ **Nota:** A busca pode levar 10-30 segundos dependendo do número de partidas.

## 📊 O Que Você Verá:

✅ **Informações do Jogador:**
- Foto de perfil
- Nome e Tag
- Nível do invocador

✅ **Estatísticas Gerais:**
- Total de partidas analisadas
- Vitórias e derrotas
- Taxa de vitória (%)

✅ **Top 5 Campeões:**
- Campeões mais jogados
- Estatísticas por campeão
- KDA médio
- Taxa de vitória por campeão

✅ **Jogadores Frequentes:**
- Com quem você mais joga
- Estatísticas de partidas em dupla
- Taxa de vitória jogando juntos

## ❌ Problemas Comuns:

### Erro 401 (Unauthorized)
- Sua API key está incorreta ou expirou
- API keys de desenvolvimento expiram em 24 horas
- Obtenha uma nova key em https://developer.riotgames.com/

### Erro 404 (Not Found)
- Nome do jogador ou tag incorretos
- Jogador não existe na região configurada
- Certifique-se de usar a tag correta (BR1, KR1, etc)

### Erro 429 (Too Many Requests)
- Você atingiu o limite de requisições da API
- Aguarde alguns minutos
- Reduza o número de partidas a serem analisadas

### "Carregando..." por muito tempo
- API key não configurada
- Problemas de conexão com a API do Riot
- Verifique os logs no terminal para detalhes

## 🌍 Mudando de Região:

Para usar outras regiões, edite `application.properties`:

**Brasil (padrão):**
```properties
riot.api.base.url=https://br1.api.riotgames.com
riot.api.americas.url=https://americas.api.riotgames.com
```

**Europa:**
```properties
riot.api.base.url=https://euw1.api.riotgames.com
riot.api.americas.url=https://europe.api.riotgames.com
```

**América do Norte:**
```properties
riot.api.base.url=https://na1.api.riotgames.com
riot.api.americas.url=https://americas.api.riotgames.com
```

**Ásia/Coreia:**
```properties
riot.api.base.url=https://kr.api.riotgames.com
riot.api.americas.url=https://asia.api.riotgames.com
```

## 📝 Notas Importantes:

⚠️ **API Key de Desenvolvimento:**
- Limite: 20 requisições por segundo, 100 por 2 minutos
- Validade: 24 horas
- Deve ser renovada diariamente

💡 **Dicas:**
- Comece com 10-20 partidas para testes rápidos
- Mais partidas = análise mais completa, mas demora mais
- A aplicação espera 100ms entre cada requisição para evitar rate limiting

🎯 **Performance:**
- 10 partidas: ~5 segundos
- 20 partidas: ~10 segundos
- 50 partidas: ~25 segundos
- 100 partidas: ~50 segundos

---

**Status Atual:** ✅ Aplicação rodando em http://localhost:8080

**Próximo Passo:** Configure sua API key e comece a usar!
