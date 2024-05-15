# GDLANDROIDKOTLINPROJECTLAB Recipes App

![omelette_5294646](https://github.com/DonSaul/GDLANDROIDKOTLINPROJECTLAB/assets/147429296/1d323d62-e43e-46cc-b337-620521689d55)

## Description
Application based on preferences and personal taste of users who love to cook and share recipes. Designed to enhance your cooking experience, whether you’re a seasoned chef, a kitchen novice or simply want to taste and find new dishes form around the world.<br/>
With user-friendly features, personalized recommendations let's you find and share your favorite dishes!


## Installation

TargetSDK = 34 <br/>
MinimumSDK = 24 <br/>
jvm (Java Version) = 1.8

## Dependencies 
```kotlin
    //Navigation
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //Lottie Files
    implementation ("com.airbnb.android:lottie-compose:5.2.0")

    //icon
    implementation("androidx.compose.material:material-icons-extended:1.6.5")

    //ROOM DB
    val room_version = "2.6.1"
    implementation ("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    //Retrofit 
    implementation(libs.retrofit){
        exclude(module = "okhttp")
    }
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.okhttp)
    implementation(libs.retrofit.urlConnection)
    implementation(libs.retrofit.logging)
    implementation(libs.gson)
    implementation(libs.dagger.hilt.compose)

    //Navigation animation
    implementation("com.exyte:animated-navigation-bar:1.0.0")
```

## API (BACKEND)
The app gets the data from spoonacular API (https://spoonacular.com/food-api) <br/>
Free subscription (150 points per day)

## Usage - MainScreen
First screen called after opening App. Required paramenters, modifier, Navigation Controller and Id change action
```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onIdSelectedChange: (Int) -> Unit
){

}
```

## Local Data Base configuration
Included three DAOs for local DB. Favorites, Seen History and Search History 
```kotlin
@Database(entities = [FavoriteEntity::class,SeenRecipeEntity::class, SearchHistoryEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class RecipesDB: RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao

    abstract fun seenDao(): SeenDao

    abstract fun searchHistoryDao(): SearchHistoryDao
}
```

## Search recipe (ViewModel example)
```kotlin
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
    private val getSearchRecipesUseCase: GetSearchRecipesUseCase,
    private val getSimilarRecipesUseCase: GetSimilarRecipesUseCase,
    private val getRecipesInformationBulkUseCase: GetRecipesInformationBulkUseCase,
    application: Application
) : AndroidViewModel(application) {

  private val _state = MutableStateFlow<State<RecipeSearch>>(State.Loading)
  val state = _state as StateFlow<State<RecipeSearch>>

  //Search recipe 
  fun getSearchRecipes(query: String = "", diet: String = "") {
        viewModelScope.launch {
            _state.value = State.Loading
            try {

                val result = getSearchRecipesUseCase.invoke(API_KEY,query,diet)
                _state.value = State.Success(result)

            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }
}
```

## MainActivity

Description of MainActivity
```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            RecipesAppTheme (darkTheme = false) {
                SetUpNavGraph(navController = navController)
            }
        }
    }
}
```


