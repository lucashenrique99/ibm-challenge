import { useEffect, useState } from 'react';
import './App.css';
import Form from './components/form/Form';
import List from './components/list/List';
import { TaskService } from './service/TaskService';

function getNewTask() {
  return { id: null, description: '' };
}


function App() {
  const [tasks, setTasks] = useState([]);
  const [currentEdited, setCurrentEdited] = useState(getNewTask());

  const onEdit = (task) => {
    TaskService.findById(task.id, (data) => setCurrentEdited(data));
  };


  const onRemove = (task) => {
    TaskService.deleteById(task.id, () => setTasks(tasks.filter(t => t.id !== task.id)));
  };


  const onSave = (task) => {
    const found = tasks.find(t => t.id === task.id);
    if (found) {
      TaskService.update(task.id, task, (saved) => {
        const index = tasks.indexOf(found);
        tasks[index] = saved;
        setTasks([...tasks])
      });

    } else {
      TaskService.insert(task, (saved) => setTasks([...tasks, saved]));
    }

    setCurrentEdited(getNewTask());
  }

  useEffect(() => {
    TaskService.findAll((data) => setTasks(data))
  }, [])

  return (

    <div className="main-container">
      <h1>To Do List</h1>
      <Form current={currentEdited} onSave={onSave} />
      <List tasks={tasks} onEdit={onEdit} onRemove={onRemove} />
    </div>
  );
}

export default App;
