import { api } from "@/libs/api";
import type { Player } from "@/types/Player";

export async function listPlayers(): Promise<Player[]> {
  const { data } = await api.get<Player[]>("/players");
  console.log(data);
  return data;
}
