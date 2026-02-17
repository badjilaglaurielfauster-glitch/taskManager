<script setup>
import { ref, onMounted } from 'vue';
import TaskService from '@/service/TaskService.js';

const tasks = ref([]);
const newTaskTitle = ref('');
const userId = 1;

onMounted(async () => {
  try {
    const response = await TaskService.getTasksByUserId(userId);
    tasks.value = response.data;
  } catch (error) {
    console.error("Erreur chargement :", error);
  }
});

const addTask = async () => {
  if (!newTaskTitle.value.trim()) return;
  try {
    const response = await TaskService.createTask(userId, newTaskTitle.value);
    tasks.value.push(response.data);
    newTaskTitle.value = '';
  } catch (err) {
    console.error("Erreur ajout :", err);
  }
};

const removeTask = async (taskId) => {
  try {
    await TaskService.deleteTask(taskId);
    tasks.value = tasks.value.filter(t => t.id !== taskId);
  } catch (err) {
    console.error("Erreur suppression :", err);
  }
};

const toggleTask = async (task) => {
  try {
    await TaskService.toggleTask(task.id);
    task.completed = !task.completed;
  } catch (err) {
    console.error("Erreur toggle :", err);
  }
};
</script>

<template>
  <div class="task-card">
    <header>
      <h1>Mes Tâches</h1>
      <p>Utilisateur #{{ userId }}</p>
    </header>

    <div class="input-group">
      <input
          v-model="newTaskTitle"
          @keyup.enter="addTask"
          placeholder="Qu'y a-t-il à faire ?"
      />
      <button @click="addTask" class="btn-add">Ajouter</button>
    </div>

    <ul class="task-list">
      <li v-for="task in tasks" :key="task.id" class="task-item">
        <div class="task-content">
          <input
              type="checkbox"
              :checked="task.completed"
              @change="toggleTask(task)"
          />
          <span :class="{ 'completed': task.completed }">
            {{ task.title }}
          </span>
        </div>

        <button @click="removeTask(task.id)" class="btn-delete" title="Supprimer">
          &times;
        </button>
      </li>
    </ul>

    <div v-if="tasks.length === 0" class="empty-state">
      Aucune tâche pour le moment.
    </div>
  </div>
</template>

<style scoped>
.task-card {
  max-width: 500px;
  margin: 2rem auto;
  padding: 2rem;
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

header h1 {
  margin: 0;
  color: #1a202c;
  font-size: 1.5rem;
}

header p {
  color: #718096;
  font-size: 0.875rem;
}

.input-group {
  display: flex;
  gap: 10px;
  margin: 1.5rem 0;
}

input[type="text"] {
  flex: 1;
  padding: 0.75rem;
  border: 1px solid #e2e8f0;
  border-radius: 6px;
  outline: none;
}

input[type="text"]:focus {
  border-color: #42b883;
}

.btn-add {
  background: #42b883;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 6px;
  cursor: pointer;
  font-weight: bold;
}

.task-list {
  list-style: none;
  padding: 0;
}

.task-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.75rem;
  border-bottom: 1px solid #f7fafc;
  transition: background 0.2s;
}

.task-item:hover {
  background: #f8fafc;
}

.task-content {
  display: flex;
  align-items: center;
  gap: 12px;
}

.completed {
  text-decoration: line-through;
  color: #a0aec0;
}

.btn-delete {
  background: transparent;
  color: #e53e3e;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  padding: 0 5px;
}

.btn-delete:hover {
  color: #c53030;
}

.empty-state {
  text-align: center;
  color: #a0aec0;
  margin-top: 2rem;
}
</style>