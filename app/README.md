Taskify — это приложение для управления задачами с функцией напоминаний. Пользователи могут добавлять, просматривать и удалять задачи, а также получать уведомления о предстоящих задачах.                                                     
                    
## Архитектура          
          
Приложение построено с использованием архитектурных паттернов MVVM и DI (внедрение зависимостей) с помощью **Hilt**.            
          
- **ViewModel** отвечает за получение данных и управление логикой приложения.            
- **Fragment** — UI-компоненты, в которых отображаются данные и принимаются пользовательские входные данные.            
- **Room** — база данных для хранения задач.            
- **WorkManager** — для планирования уведомлений о задачах.            
- **Dependency injection (DI) (Hilt)** используется для упрощения внедрения зависимостей в компоненты приложения.            
          
## Компоненты       

### 1. MainActivity      
Общая Activity приложения, которая содержит главную навигацию.      
      
- **Функции**:      
    - Устанавливает ActionBar.      
    - Настраивает навигацию через `NavController`.      
      
### 2. Task      
Модель данных для представления задачи.      
      
- **Свойства**:      
    - `id`: Уникальный идентификатор задачи.      
    - `name`: Имя задачи.      
    - `time`: Время выполнения задачи.      
    - `date`: Дата выполнения задачи.      
      
### 3. TaskReminderWorker      
Создает и отправляет уведомления о задачах.      
      
- **Функции**:      
    - Создает канал уведомлений (для API 26 и выше).      
    - Отправляет уведомление при выполнении работы.      
      
### 4. TaskAdapter      
Адаптер для отображения списка задач.      
      
- **Функции**:      
    - Управляет отображением элементов списка в `RecyclerView`.      
    - Поддерживает удаление задач с помощью слушателя.      
      
### 5. AddTaskFragment      
Фрагмент для добавления новой задачи.      
      
- **Функции**:      
    - Позволяет пользователю вводить данные о задаче (имя, дата, время).      
    - Сохраняет задачу в базе данных и планирует уведомление.      
      
### 6. TaskListFragment      
Фрагмент для отображения списка задач.      

- **Функции**:      
    - Загружает и отображает все задачи из базы данных в виде списка.      
    - Обеспечивает возможность навигации к `AddTaskFragment`.      
      
### 7. WelcomeFragment      
Фрагмент приветствия для навигации на список задач.      
      
- **Функции**:      
    - Перенаправляет пользователя на `TaskListFragment`.      
      
### 8. BootReceiver      
Получает уведомления о перезагрузках устройства и перестраивает напоминания о задачах.      
      
- **Функции**:      
    - Перезапускает напоминания при старте устройства.      
      
### 9. ViewModel (TaskViewModel)      
Управляет запросами к репозиторию задач.      
      
- **Функции**:      
    - Позволяет добавлять и удалять задачи.      
      
### 10. Репозиторий (TaskRepository)      
Связывает интерфейс DAO с ViewModel.      
      
- **Функции**:      
    - Обрабатывает данные задач и предоставляет их ViewModel.      
      
### 11. База данных (TaskDatabase)      
База данных для хранения задач с использованием Room.      
      
### 12. DAO (TaskDao)      
Интерфейс для доступа к задачам в базе данных.      
      
## Разрешения в манифесте      
      
- `POST_NOTIFICATIONS`: Позволяет приложению отправлять уведомления.      
- `RECEIVE_BOOT_COMPLETED`: Позволяет приложению получать уведомления о завершении загрузки устройства.      
      
## Зависимости      
      
Необходимые зависимости, используемые в приложении:      
      
```groovy      
dependencies {
    implementation("androidx.core:core-ktx:1.10.1")      
    implementation("androidx.appcompat:appcompat:1.7.0")      
    implementation("com.google.android.material:material:1.12.0")      
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")      
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.2")      
    implementation("androidx.navigation:navigation-ui-ktx:2.7.2")      
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")      
    implementation("androidx.work:work-runtime-ktx:2.8.1")      
      
    // Room      
    implementation("androidx.room:room-runtime:2.5.0")      
    kapt("androidx.room:room-compiler:2.5.0")      
    implementation("androidx.room:room-ktx:2.5.0")      
      
    // Hilt      
    implementation("com.google.dagger:hilt-android:2.48")      
    kapt("com.google.dagger:hilt-compiler:2.48")      
      
    // Testing      
    testImplementation("junit:junit:4.13.2")      
    androidTestImplementation("androidx.test.ext:junit:1.1.5")      
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")          
}      
```      
      
 
      
      

      
   