import { HashRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/layout/Header.jsx";
import Footer from "./components/layout/Footer.jsx";
import Home from "./components/pages/Home.jsx";
import About from "./components/pages/About.jsx";
import ViewTask from "./components/pages/ViewTask.jsx";
import EditTask from "./components/common/EditTask.jsx";
import { useState } from "react";
import "./Index.css";
import "react-day-picker/style.css";
import LogIn from "./components/pages/LogIn.jsx";
import sampleTasks from "./sampleData/sampleTasks.js";

function App() {
  const [logInStatus, setLogInStatus] = useState(false); 
  const [taskArray, setTaskArray] = useState(sampleTasks);
  const [clicked, setClicked] = useState(0);

  const saveTask = (updatedTask) => {
    setTaskArray((curArray) => {
      const taskIndex = curArray.findIndex(
        (t) => t.taskId === updatedTask.taskId,
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
