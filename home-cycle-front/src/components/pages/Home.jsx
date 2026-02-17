import TasksSection from "../layout/TasksSection";

const Home = ({ taskArray, saveTask }) => {
  return <TasksSection taskArray={taskArray} saveTask={saveTask} />;
};

export default Home;
