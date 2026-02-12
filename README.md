# League of Legends Stats Application

Spring Boot application that consumes the Riot Games API to display detailed statistics of League of Legends players.

## 📋 Features

- ✅ Player search by name and tag
- ✅ Player profile picture
- ✅ Total matches, wins and losses
- ✅ Win percentage
- ✅ Top 5 most played champions with detailed statistics
- ✅ Win rate per champion
- ✅ List of frequent players (friends)
- ✅ Match statistics played with each friend
- ✅ Simple and responsive web interface

## 🚀 How to Use

### 1. Get Riot Games API Key

1. Go to: https://developer.riotgames.com/
2. Login with your Riot account
3. Copy your Development API Key

### 2. Configure the Application

Edit the `src/main/resources/application.properties` file:

```properties
riot.api.key=YOUR-API-KEY-HERE
```

**Important:** Replace `YOUR-API-KEY-HERE` with your actual Riot Games API key.

### 3. Run the Application

#### Option 1: Using Maven Wrapper (Recommended)
```bash
./mvnw spring-boot:run
```

#### Option 2: Using installed Maven
```bash
mvn spring-boot:run
```

#### Option 3: Generating JAR and running
```bash
mvn clean package
java -jar target/lol-stats-1.0.0.jar
```

### 4. Access the Application

Open your browser and go to:
```
http://localhost:8080
```

## 🎮 How to Search for a Player

1. Enter the player name (e.g., "Hide on bush")
2. Enter the tag (e.g., "KR1" or "BR1")
3. Choose how many matches you want to analyze (default: 20)
4. Click "Search"

**Examples of famous players:**
- Faker: `Hide on bush#KR1`
- Caps: `Caps#EUW`

## 📁 Project Structure

```
LoL/
├── src/
│   ├── main/
│   │   ├── java/com/lol/stats/
│   │   │   ├── LolStatsApplication.java      # Main class
│   │   │   ├── config/
│   │   │   │   └── RiotApiConfig.java        # API configurations
│   │   │   ├── controller/
│   │   │   │   ├── PlayerController.java     # REST endpoints
│   │   │   │   └── HomeController.java       # Home page controller
│   │   │   ├── dto/
│   │   │   │   ├── AccountDto.java           # DTO for Account API
│   │   │   │   ├── SummonerDto.java          # DTO for Summoner API
│   │   │   │   └── MatchDto.java             # DTO for Match API
│   │   │   ├── model/
│   │   │   │   ├── PlayerStats.java          # Player statistics model
│   │   │   │   ├── ChampionStats.java        # Statistics per champion
│   │   │   │   └── FriendStats.java          # Friend statistics
│   │   │   └── service/
│   │   │       └── RiotApiService.java       # API integration service
│   │   └── resources/
│   │       ├── application.properties        # Configuration
│   │       └── static/
│   │           ├── index.html                # Web interface
│   │           ├── styles.css                # Styles
│   │           └── script.js                 # JavaScript
│   └── test/
└── pom.xml                                   # Maven dependencies
```

## 🔌 API Endpoints

### GET /api/player/stats

Fetches statistics for a player.

**Parameters:**
- `gameName` (required): Player name
- `tagLine` (required): Player tag (e.g., BR1)
- `matchCount` (optional): Number of matches to analyze (default: 20, max: 100)

**Example:**
```
GET http://localhost:8080/api/player/stats?gameName=Faker&tagLine=KR1&matchCount=20
```

**Response:**
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

## 🛠️ Technologies Used

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
  - Spring Web
  - Spring Boot DevTools
  - Validation
- **Lombok** - Boilerplate code reduction
- **Jackson** - JSON processing
- **RestTemplate** - HTTP client

### Frontend
- **HTML5**
- **CSS3** - Responsive design and gradients
- **JavaScript (Vanilla)** - REST API consumption
- **Fetch API** - Asynchronous requests

### External APIs
- **Riot Games API**
  - Account-v1: Account information
  - Summoner-v4: Summoner data
  - Match-v5: Match history

## ⚠️ Limitations and Notes

1. **Development API Key**: The free API key has request limits (20 requests per second, 100 per 2 minutes)
2. **Region**: Configured for BR1 (Brazil). For other regions, edit `application.properties`
3. **Rate Limiting**: The code includes delays to avoid rate limiting
4. **Cache**: No cache implemented, each search makes new API requests

## 🌍 Region Configuration

To use other regions, edit the `application.properties`:

```properties
# For Europe:
riot.api.base.url=https://euw1.api.riotgames.com
riot.api.americas.url=https://europe.api.riotgames.com

# For North America:
riot.api.base.url=https://na1.api.riotgames.com
riot.api.americas.url=https://americas.api.riotgames.com

# For Asia:
riot.api.base.url=https://kr.api.riotgames.com
riot.api.americas.url=https://asia.api.riotgames.com
```

## 📊 Statistics Collected

### Per Player
- Name and Tag
- Profile icon
- Summoner level
- Total matches analyzed
- Wins and losses
- Overall win rate

### Per Champion
- Champion name
- Number of matches
- Wins and losses
- Win rate
- Average KDA (Kills/Deaths/Assists)

### Frequent Players
- Player name
- Matches played together
- Wins and losses as duo
- Win rate as duo

## 🐛 Troubleshooting

### Error 401 (Unauthorized)
- Check if the API key is correct in `application.properties`
- Make sure the API key hasn't expired (development keys expire in 24h)

### Error 404 (Not Found)
- Verify that the player name and tag are correct
- Make sure the player exists in the configured region

### Error 429 (Too Many Requests)
- You've reached the request limit
- Wait a few minutes before trying again
- Reduce the number of matches to be analyzed

## 📝 License

This project is open source and available for educational purposes.

## 👨‍💻 Development

To contribute or modify the project:

1. Clone the repository
2. Import as a Maven project
3. Configure your API key
4. Run the application
5. Make your modifications
6. Test locally

## 📞 Support

For issues related to the Riot Games API, refer to:
- Official documentation: https://developer.riotgames.com/docs/lol
- Developer portal: https://developer.riotgames.com/

---

**Note**: This project is not affiliated with, associated with, authorized by, endorsed by, or in any way officially connected with Riot Games, Inc. or any of its subsidiaries or affiliates.
