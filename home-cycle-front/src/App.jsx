import { HashRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/layout/Header.jsx";
import Footer from "./components/layout/Footer.jsx";
import Home from "./components/pages/Home.jsx";
import About from "./components/pages/About.jsx";
import ViewTask from "./components/pages/ViewTask.jsx";
import EditTask from "./components/common/EditTask.jsx";
import { useEffect, useState } from "react";
import "./Index.css";
import "react-day-picker/style.css";
import LogIn from "./components/pages/LogIn.jsx";
import { taskService } from "./components/services/taskService.js";
import { userService } from "./components/services/userService.js";
import { useNavigate }  from "react-router";

function App() {
  const navigate = useNavigate();
  const [logInStatus, setLogInStatus] = useState(!!localStorage.getItem("user_token")); 
  const [taskArray, setTaskArray] = useState([]);
  const [clicked, setClicked] = useState(0);
  const [refreshToggle, setRefreshToggle] = useState(false);

  const saveTask = (updatedTask) => {
    setTaskArray((curArray) => {
      const taskIndex = curArray.findIndex(
        (t) => t.id === updatedTask.id,
      );

      if (taskIndex !== -1) {
        const updatedArray = [...curArray];
        updatedArray[taskIndex] = updatedTask;
        return updatedArray;
      } else {
        return [...curArray, updatedTask];
      }
    });
  };

  // handle logout
  const handleLogout = async () => {
    try {
      await userService.logout();
    } catch (er) {
      console.error("Server-side logout failed:", er);
    } finally {
      localStorage.clear();
      setLogInStatus(false);
      setTaskArray([]);
      navigate("/login")
    }
  };

  // Task Exclusion from state (after delete sent to API)
  const removeTaskFromState = (id) => {
    setTaskArray((curArray) => {
      return curArray.filter((t) => t.id !== id);
    });
  };

  const fetchAllTasks = async () => {
    try {
      const response = await taskService.getTasks();
      setTaskArray(response.data);
    } catch (er) {
      console.error("Error fetching tasks:", er);
      // TODO: Add error handling UI
    }
  };

  const refreshTasks = () => setRefreshToggle((prev) => !prev);

  // API Task Load
  useEffect(() => {
    fetchAllTasks();
  }, [logInStatus, refreshToggle]);

  // Page Layout and routing
  return (
    <>
        <Header
          logInStatus={logInStatus}
          handleLogout={handleLogout}
          clicked={clicked}
        />
        {logInStatus ? (
          <Routes>
            <Route
              path="/"
              element={<Home taskArray={taskArray} saveTask={saveTask} triggerRefresh={refreshTasks}/>}
            />
            <Route path="/about" element={<About />} />
            <Route
              path="/task/:id"
              element={<ViewTask taskArray={taskArray} saveTask={saveTask} triggerRefresh={refreshTasks}/>}
            />
            <Route
              path="/task/:id/edit"
              element={<EditTask tasks={taskArray} saveTask={saveTask} key="edit-task" removeTaskFromState={removeTaskFromState} triggerRefresh={refreshTasks}/>}
            />
            <Route
              path="/newtask"
              element={<EditTask tasks={taskArray} saveTask={saveTask} key="new-task" removeTaskFromState={removeTaskFromState} triggerRefresh={refreshTasks}/>}
            />
            <Route path="*" element={<Home />} />
          </Routes>
        ) : (
          <Routes>
            <Route
              path="/"
              element={
                <LogIn
                  logInStatus={logInStatus}
                  setLogInStatus={setLogInStatus}
                  clicked={clicked}
                  setClicked={setClicked}
                />
              }
            />
            <Route path="/about" element={<About />} />
            <Route
              path="*"
              element={<LogIn setLogInStatus={setLogInStatus} />}
            />
          </Routes>
        )}
        <Footer />
    </>
  );
}

export default App;