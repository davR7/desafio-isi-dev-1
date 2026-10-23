import { createBrowserRouter, Navigate } from "react-router-dom";
import { Layout } from "../layouts";
import { ClubDetailPage } from "../pages/clubs/club-detail";
import { ClubsPage } from "../pages/clubs/clubs";
import { NotFoundPage } from "../pages/errors/not-found";
import { PlayerDetailPage } from "../pages/players/player-detail";
import { PlayersPage } from "../pages/players/players";

export const router = createBrowserRouter([
  {
    element: <Layout />,
    children: [
      {
        path: "/",
        element: <Navigate to="/players" replace />,
      },
      {
        path: "/players",
        element: <PlayersPage />,
      },
      {
        path: "/players/:playerId",
        element: <PlayerDetailPage />,
      },
      {
        path: "/clubs",
        element: <ClubsPage />,
      },
      {
        path: "/clubs/:clubId",
        element: <ClubDetailPage />,
      },
    ],
  },
  {
    path: "*",
    element: <NotFoundPage />,
  },
]);
