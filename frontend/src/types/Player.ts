export type PlayerPosition = "GOL" | "ZAG" | "LAT" | "MEI" | "ATA";

export type PlayerCondition = "APTO" | "MAL_CONDICIONADO" | "DOENTE";

export interface Player {
  id: number;
  name: string;
  age: number;
  position: PlayerPosition;
  condition: PlayerCondition;
  agility: number;
  pace: number;
  shooting: number;
  passing: number;
  dribbling: number;
  defending: number;
  physical: number;
  potential: number;
  overall: number;
  base_value: number;
  market_value: number;
  club_id: number | null;
  club_name: number | null;
}
