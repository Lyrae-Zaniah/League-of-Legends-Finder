# League of Legends Stats Application

Aplicação Spring Boot que consome a API do Riot Games para exibir estatísticas detalhadas de jogadores do League of Legends.

## 📋 Funcionalidades

- ✅ Busca de jogadores por nome e tag
- ✅ Foto de perfil do jogador
- ✅ Total de partidas, vitórias e derrotas
- ✅ Porcentagem de vitória
- ✅ Top 5 campeões mais usados com estatísticas detalhadas
- ✅ Taxa de vitória por campeão
- ✅ Lista de jogadores frequentes (amigos)
- ✅ Estatísticas de partidas jogadas com cada amigo
- ✅ Interface web simples e responsiva

## 🚀 Como Usar

### 1. Obter API Key do Riot Games

1. Acesse: https://developer.riotgames.com/
2. Faça login com sua conta Riot
3. Copie sua Development API Key

### 2. Configurar a Aplicação

Edite o arquivo `src/main/resources/application.properties`:

```properties
riot.api.key=SUA-API-KEY-AQUI
```

**Importante:** Substitua `SUA-API-KEY-AQUI` pela sua API key real do Riot Games.

### 3. Executar a Aplicação

#### Opção 1: Usando Maven Wrapper (Recomendado)
```bash
./mvnw spring-boot:run
```

#### Opção 2: Usando Maven instalado
```bash
mvn spring-boot:run
```

#### Opção 3: Gerando JAR e executando
```bash
mvn clean package
java -jar target/lol-stats-1.0.0.jar
```

### 4. Acessar a Aplicação

Abra seu navegador e acesse:
```
http://localhost:8080
```

## 🎮 Como Buscar um Jogador

1. Digite o nome do jogador (ex: "Hide on bush")
2. Digite a tag (ex: "KR1" ou "BR1")
3. Escolha quantas partidas deseja analisar (padrão: 20)
4. Clique em "Buscar"

**Exemplo de jogadores famosos:**
- Faker: `Hide on bush#KR1`
- Caps: `Caps#EUW`

## 📁 Estrutura do Projeto

```
LoL/
├── src/
│   ├── main/
│   │   ├── java/com/lol/stats/
│   │   │   ├── LolStatsApplication.java      # Classe principal
│   │   │   ├── config/
│   │   │   │   └── RiotApiConfig.java        # Configurações da API
│   │   │   ├── controller/
│   │   │   │   ├── PlayerController.java     # Endpoints REST
│   │   │   │   └── HomeController.java       # Controller para página inicial
│   │   │   ├── dto/
│   │   │   │   ├── AccountDto.java           # DTO para Account API
│   │   │   │   ├── SummonerDto.java          # DTO para Summoner API
│   │   │   │   └── MatchDto.java             # DTO para Match API
│   │   │   ├── model/
│   │   │   │   ├── PlayerStats.java          # Modelo de estatísticas do jogador
│   │   │   │   ├── ChampionStats.java        # Estatísticas por campeão
│   │   │   │   └── FriendStats.java          # Estatísticas de amigos
│   │   │   └── service/
│   │   │       └── RiotApiService.java       # Serviço de integração com API
│   │   └── resources/
│   │       ├── application.properties        # Configurações
│   │       └── static/
│   │           ├── index.html                # Interface web
│   │           ├── styles.css                # Estilos
│   │           └── script.js                 # JavaScript
│   └── test/
└── pom.xml                                   # Dependências Maven
```

## 🔌 Endpoints da API

### GET /api/player/stats

Busca estatísticas de um jogador.

**Parâmetros:**
- `gameName` (obrigatório): Nome do jogador
- `tagLine` (obrigatório): Tag do jogador (ex: BR1)
- `matchCount` (opcional): Número de partidas a analisar (padrão: 20, máx: 100)

**Exemplo:**
```
GET http://localhost:8080/api/player/stats?gameName=Faker&tagLine=KR1&matchCount=20
```

**Resposta:**
```json
{
  "gameName": "Faker",
  "tagLine": "KR1",
  "profileIconId": 4568,
  "summonerLevel": 542,
  "totalGames": 20,
  "wins": 13,
  "losses": 7,
  "winRate": 65.0,
  "topChampions": [...],
  "friends": [...]
}
```

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
  - Spring Web
  - Spring Boot DevTools
  - Validation
- **Lombok** - Redução de código boilerplate
- **Jackson** - Processamento JSON
- **RestTemplate** - Cliente HTTP

### Frontend
- **HTML5**
- **CSS3** - Design responsivo e gradientes
- **JavaScript (Vanilla)** - Consumo da API REST
- **Fetch API** - Requisições assíncronas

### APIs Externas
- **Riot Games API**
  - Account-v1: Informações de conta
  - Summoner-v4: Dados do invocador
  - Match-v5: Histórico de partidas

## ⚠️ Limitações e Observações

1. **API Key de Desenvolvimento**: A API key gratuita tem limite de requisições (20 requisições por segundo, 100 por 2 minutos)
2. **Região**: Configurado para BR1 (Brasil). Para outras regiões, edite `application.properties`
3. **Rate Limiting**: O código inclui delays para evitar rate limiting
4. **Cache**: Não há cache implementado, cada busca faz novas requisições à API

## 🌍 Configuração de Regiões

Para usar outras regiões, edite o `application.properties`:

```properties
# Para Europa:
riot.api.base.url=https://euw1.api.riotgames.com
riot.api.americas.url=https://europe.api.riotgames.com

# Para América do Norte:
riot.api.base.url=https://na1.api.riotgames.com
riot.api.americas.url=https://americas.api.riotgames.com

# Para Ásia:
riot.api.base.url=https://kr.api.riotgames.com
riot.api.americas.url=https://asia.api.riotgames.com
```

## 📊 Estatísticas Coletadas

### Por Jogador
- Nome e Tag
- Ícone de perfil
- Nível do invocador
- Total de partidas analisadas
- Vitórias e derrotas
- Taxa de vitória geral

### Por Campeão
- Nome do campeão
- Quantidade de partidas
- Vitórias e derrotas
- Taxa de vitória
- KDA médio (Kills/Deaths/Assists)

### Jogadores Frequentes
- Nome do jogador
- Partidas jogadas juntos
- Vitórias e derrotas em dupla
- Taxa de vitória em dupla

## 🐛 Troubleshooting

### Erro 401 (Unauthorized)
- Verifique se a API key está correta no `application.properties`
- Certifique-se de que a API key não expirou (keys de desenvolvimento expiram em 24h)

### Erro 404 (Not Found)
- Verifique se o nome do jogador e tag estão corretos
- Certifique-se de que o jogador existe na região configurada

### Erro 429 (Too Many Requests)
- Você atingiu o limite de requisições
- Aguarde alguns minutos antes de tentar novamente
- Reduza o número de partidas a serem analisadas

## 📝 Licença

Este projeto é de código aberto e está disponível para fins educacionais.

## 👨‍💻 Desenvolvimento

Para contribuir ou modificar o projeto:

1. Clone o repositório
2. Importe como projeto Maven
3. Configure sua API key
4. Execute a aplicação
5. Faça suas modificações
6. Teste localmente

## 📞 Suporte

Para problemas relacionados à API do Riot Games, consulte:
- Documentação oficial: https://developer.riotgames.com/docs/lol
- Portal de desenvolvedores: https://developer.riotgames.com/

---

**Nota**: Este projeto não é afiliado, associado, autorizado, endossado por, ou de qualquer forma oficialmente conectado com a Riot Games, Inc. ou qualquer uma de suas subsidiárias ou afiliadas.
