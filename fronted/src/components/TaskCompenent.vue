<script setup>
import { ref, onMounted } from 'vue';
import TaskService from '@/service/TaskService.js';

const tasks = ref([]);
const newTaskTitle = ref('');
const userId = 1; // On simule l'ID de l'utilisateur connecté


onMounted(async () => {
  try {
    const response = await TaskService.getTasksByUserId(userId);
    tasks.value = response.data;
  } catch (error) {
    console.error("Erreur lors du chargement :", error);
  }
});


const addTask = async () => {
  if (!newTaskTitle.value) return;

  try {
    const response = await TaskService.createTask(userId, newTaskTitle.value);
    tasks.value.push(response.data); // On ajoute la nouvelle tâche à la liste
    newTaskTitle.value = ''; // On vide le champ
  } catch (err) {
    console.log(`Exception threw when ${err}`);
  }
};
</script>

<template>
  <div>
    <input v-model="newTaskTitle" placeholder="Nouvelle tâche..." />
    <button @click="addTask">Ajouter</button>

    <ul>
      <li v-for="task in tasks" :key="task.id">
        {{ task.title }} - {{ task.completed ? '✅' : '⏳' }}
      </li>
    </ul>
  </div>
</template>