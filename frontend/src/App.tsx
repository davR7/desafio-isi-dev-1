import { RouterProvider } from "react-router-dom";
import { router } from "./router";

function App() {
  return (
    <div className="flex min-h-screen flex-col">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
