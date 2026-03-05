import TasksSection from "../layout/TasksSection";

const Home = ({ taskArray, saveTask, triggerRefresh }) => {
  return <TasksSection taskArray={taskArray} saveTask={saveTask} triggerRefresh={triggerRefresh} />;
};

export default Home;
