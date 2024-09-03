//package com.example.taskify.JetpackCompose
//
//@Composable
//fun TaskListScreen(viewModel: TaskViewModel) {
//    val tasks by viewModel.tasks.collectAsState(initial = emptyList())
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Task List") })
//        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { /* Navigate to add task screen */ }) {
//                Icon(Icons.Default.Add, contentDescription = "Add Task")
//            }
//        }
//    ) { padding ->
//        LazyColumn(contentPadding = padding) {
//            items(tasks) { task ->
//                TaskItem(task = task, onDelete = { viewModel.removeTask(task) })
//            }
//        }
//    }
//}
//
//@Composable
//fun TaskItem(task: Task, onDelete: () -> Unit) {
//    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
//        Text(task.name, modifier = Modifier.weight(1f))
//        IconButton(onClick = onDelete) {
//            Icon(Icons.Default.Delete, contentDescription = "Delete Task")
//        }
//    }
//}