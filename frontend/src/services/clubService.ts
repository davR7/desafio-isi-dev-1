import { api } from "@/libs/api";
import type { Club } from "@/types/Club";

export async function listClubs(): Promise<Club[]> {
  const { data } = await api.get<Club[]>("/clubs");
  console.log(data);
  return data;
}
