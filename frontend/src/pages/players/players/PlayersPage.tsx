import { useQuery } from "@tanstack/react-query";
import { Container } from "@/components/Container";
import { listPlayers } from "@/services/playerService";
import type { Player, PlayerCondition } from "@/types/Player";

const conditionLabels: Record<
  PlayerCondition,
  { label: string; className: string; dotClassName: string }
> = {
  APTO: {
    label: "Apto",
    className: "border-green-200 bg-green-50 text-green-700",
    dotClassName: "bg-green-600",
  },
  MAL_CONDICIONADO: {
    label: "Mal condicionado",
    className: "border-amber-300 bg-amber-50 text-amber-800",
    dotClassName: "bg-amber-700",
  },
  DOENTE: {
    label: "Doente",
    className: "border-red-200 bg-red-50 text-red-600",
    dotClassName: "bg-red-600",
  },
};

const technicalAttributes: {
  key: keyof Pick<Player, "pace" | "shooting" | "passing" | "dribbling" | "defending" | "physical">;
  label: string;
}[] = [
  { key: "pace", label: "Ritmo" },
  { key: "shooting", label: "Finalização" },
  { key: "passing", label: "Passe" },
  { key: "dribbling", label: "Drible" },
  { key: "defending", label: "Defesa" },
  { key: "physical", label: "Físico" },
];

function formatValue(value: number): string {
  return value.toLocaleString("pt-BR", {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  });
}

function getInitials(name: string): string {
  return name
    .trim()
    .split(/\s+/)
    .slice(0, 2)
    .map((part) => part[0])
    .join("")
    .toUpperCase();
}

function PlayerAvatar({ name }: { name: string }) {
  return (
    <div className="flex size-7.5 shrink-0 items-center justify-center rounded-full bg-zinc-100 text-[10px] font-medium text-zinc-600">
      {getInitials(name)}
    </div>
  );
}

function ConditionBadge({ condition }: { condition: PlayerCondition }) {
  const config = conditionLabels[condition];

  return (
    <span
      className={`inline-flex items-center gap-1.5 whitespace-nowrap rounded-lg border px-2 py-0.75 text-[10px] font-medium ${config.className}`}
    >
      <span className={`size-1.25 rounded-full ${config.dotClassName}`} />
      {config.label}
    </span>
  );
}

function AgilityBar({ value }: { value: number }) {
  const colorClass = value <= 30 ? "bg-red-600" : value >= 80 ? "bg-emerald-600" : "bg-zinc-500";

  return (
    <div className="flex items-center gap-2">
      <span className="w-4.5 text-xs tabular-nums text-zinc-700">{value}</span>

      <div
        role="progressbar"
        aria-label={`Agilidade: ${value}`}
        aria-valuenow={value}
        aria-valuemin={0}
        aria-valuemax={100}
        className="h-1.25 w-8.75 overflow-hidden rounded-full bg-zinc-100"
      >
        <div
          className={`h-full rounded-full ${colorClass}`}
          style={{ width: `${Math.max(0, Math.min(value, 100))}%` }}
        />
      </div>
    </div>
  );
}

function PlayerAttributes({ player }: { player: Player }) {
  return (
    <details className="group">
      <summary className="cursor-pointer list-none text-[10px] text-zinc-500 hover:text-zinc-900">
        <span className="group-open:hidden">Ver atributos +</span>
        <span className="hidden group-open:inline">Ocultar atributos −</span>
      </summary>

      <div className="mt-3 grid grid-cols-2 gap-x-5 gap-y-2 sm:grid-cols-3">
        {technicalAttributes.map(({ key, label }) => (
          <div key={key} className="flex items-center gap-2 text-xs">
            <span className="w-20 text-zinc-500">{label}</span>
            <div className="h-1.5 flex-1 overflow-hidden rounded-full bg-zinc-100">
              <div
                className="h-full rounded-full bg-zinc-600"
                style={{
                  width: `${Math.max(0, Math.min(player[key], 100))}%`,
                }}
              />
            </div>
            <span className="w-6 text-right font-medium tabular-nums">{player[key]}</span>
          </div>
        ))}

        <div className="flex items-center gap-2 text-xs">
          <span className="w-20 text-zinc-500">Potencial</span>
          <span className="font-semibold tabular-nums">{player.potential}</span>
        </div>
      </div>
    </details>
  );
}

function PlayerRow({ player }: { player: Player }) {
  return (
    <tr className="transition-colors hover:bg-zinc-50">
      <td className="px-3 py-2">
        <div className="flex min-w-48.75 items-center gap-2.5">
          <PlayerAvatar name={player.name} />

          <div className="flex flex-col gap-0.5">
            <span className="text-xs font-medium text-zinc-900">{player.name}</span>
            <span className="text-[10px] text-zinc-500">{player.position}</span>
          </div>
        </div>
      </td>

      <td className="px-3 py-2">
        <span className="inline-flex rounded-lg border border-zinc-200 px-2 py-0.75 text-[10px] font-medium text-zinc-800">
          {player.position}
        </span>
      </td>

      <td className="px-3 py-2 text-center text-xs font-semibold tabular-nums">{player.overall}</td>

      <td className="px-3 py-2">
        <AgilityBar value={player.agility} />
      </td>

      <td className="px-3 py-2 text-center text-xs tabular-nums text-zinc-800">{player.age}</td>

      <td className="px-3 py-2">
        <ConditionBadge condition={player.condition} />
      </td>

      <td className="px-3 py-2 text-right text-xs tabular-nums text-zinc-500">
        {formatValue(player.base_value)}
      </td>

      <td className="px-3 py-2 text-right text-xs font-semibold tabular-nums text-zinc-900">
        {formatValue(player.market_value)}
      </td>

      <td className="px-3 py-2">
        {player.club_id === null ? (
          <span className="inline-flex rounded bg-zinc-100 px-1.5 py-1 text-[10px] text-zinc-600">
            Livre
          </span>
        ) : (
          <span className="inline-flex rounded bg-zinc-100 px-1.5 py-1 text-[10px] font-medium text-zinc-700">
            {player.club_name}
          </span>
        )}
      </td>

      <td className="px-3 py-2">
        <PlayerAttributes player={player} />
      </td>
    </tr>
  );
}

export function PlayersPage() {
  const {
    data: players,
    isPending,
    isError,
    error,
    refetch,
  } = useQuery<Player[], Error>({
    queryKey: ["players"],
    queryFn: listPlayers,
  });

  if (isPending) {
    return (
      <div className="flex min-h-40 items-center justify-center text-sm text-zinc-500">
        Carregando jogadores...
      </div>
    );
  }

  if (isError) {
    return (
      <div className="flex flex-col items-center gap-3 py-12 text-center">
        <p className="text-sm text-red-600">Não foi possível carregar os jogadores.</p>
        <p className="text-xs text-zinc-500">{error.message}</p>
        <button
          type="button"
          onClick={() => refetch()}
          className="rounded-lg border border-zinc-200 px-3 py-2 text-xs font-medium hover:bg-zinc-50"
        >
          Tentar novamente
        </button>
      </div>
    );
  }

  return (
    <Container>
      <header className="flex flex-wrap items-center justify-between gap-4 my-4">
        <div>
          <h1 className="text-2xl font-semibold tracking-tight">Players</h1>
          <p className="mt-1 text-sm text-zinc-500">
            Gerencie e acompanhe os jogadores cadastrados.
          </p>
        </div>

        <span className="rounded-lg border border-zinc-200 px-3 py-1.5 text-xs text-zinc-600">
          {players.length} jogadores
        </span>
      </header>

      <section className="overflow-hidden rounded-lg border border-zinc-200 bg-white">
        <div className="overflow-x-auto">
          <table className="w-full min-w-262.5 border-collapse text-left">
            <thead className="bg-zinc-50">
              <tr>
                {[
                  "Jogador",
                  "Posição",
                  "Overall",
                  "Agilidade",
                  "Idade",
                  "Condição",
                  "Valor base",
                  "Valor de mercado",
                  "Clube",
                  "Atributos",
                ].map((label) => (
                  <th
                    key={label}
                    className="h-9 border-b border-zinc-200 px-3 text-[11px] font-medium whitespace-nowrap text-zinc-500"
                  >
                    {label}
                  </th>
                ))}
              </tr>
            </thead>

            <tbody className="divide-y divide-zinc-200">
              {players.map((player) => (
                <PlayerRow key={player.id} player={player} />
              ))}
            </tbody>
          </table>
        </div>

        {players.length === 0 && (
          <div className="px-4 py-12 text-center text-sm text-zinc-500">
            Nenhum jogador cadastrado.
          </div>
        )}
      </section>
    </Container>
  );
}
