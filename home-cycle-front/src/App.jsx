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

function App() {
  const [logInStatus, setLogInStatus] = useState(false); 
  const [taskArray, setTaskArray] = useState([]);
  const [clicked, setClicked] = useState(0);

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

  // API Task Load
  useEffect(() => {
    const fetchAllTasks = async () => {
      try {
        const response = await taskService.getTasks();
        setTaskArray(response.data);
      } catch (er) {
        console.error("Error fetching tasks:", er);
        // TODO: Add error handling UI
      }
    };
    fetchAllTasks();
  }, []);

  // Page Layout and routing
  return (
    <>
      <Router id="main-content">
        <Header
          logInStatus={logInStatus}
          setLogInStatus={setLogInStatus}
          setClicked={setClicked}
          clicked={clicked}
        />
        {logInStatus ? (
          <Routes>
            <Route
              path="/"
              element={<Home taskArray={taskArray} saveTask={saveTask} />}
            />
            <Route path="/about" element={<About />} />
            <Route
              path="/task/:id"
              element={<ViewTask taskArray={taskArray} saveTask={saveTask} />}
            />
            <Route
              path="/task/:id/edit"
              element={<EditTask tasks={taskArray} saveTask={saveTask} key="edit-task"/>}
            />
            <Route
              path="/newtask"
              element={<EditTask tasks={taskArray} saveTask={saveTask} key="new-task"/>}
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
      </Router>
    </>
  );
}

export default App;
