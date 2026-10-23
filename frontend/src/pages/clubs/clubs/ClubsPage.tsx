import { useQuery } from "@tanstack/react-query";
import { Link } from "react-router-dom";
import { Container } from "@/components/Container";
import { listClubs } from "@/services/clubService";
import type { Club } from "@/types/Club";

function formatValue(value: number): string {
  return value.toLocaleString("pt-BR", {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  });
}

const INITIAL_CASH = 300;
const MAX_CLUBS = 20;

const COLUMNS = [
  { label: "Clube", align: "left" },
  { label: "Sigla", align: "left" },
  { label: "Caixa", align: "right" },
  { label: "Valor do elenco", align: "right" },
  { label: "Patrimônio", align: "right" },
  { label: "Ações", align: "right", srOnly: true },
] as const;

function ClubAvatar({ acronym }: { acronym: string }) {
  return (
    <span
      className="flex size-8 shrink-0 items-center justify-center rounded-md border border-zinc-200 bg-zinc-50 text-[9px] font-semibold text-zinc-700"
      aria-hidden="true"
    >
      {acronym}
    </span>
  );
}

function ClubRow({ club }: { club: Club }) {
  return (
    <tr className="transition-colors hover:bg-zinc-50">
      <td className="px-3 py-2">
        <div className="flex min-w-48.75 items-center gap-2.5">
          <ClubAvatar acronym={club.acronym} />

          <span className="text-xs font-medium text-zinc-900">{club.name}</span>
        </div>
      </td>

      <td className="px-3 py-2">
        <span className="inline-flex rounded-lg border border-zinc-200 px-2 py-0.75 text-[10px] font-medium text-zinc-800">
          {club.acronym}
        </span>
      </td>

      <td className="px-3 py-2 text-right text-xs tabular-nums text-zinc-800">
        {formatValue(club.cash)}
      </td>

      <td className="px-3 py-2 text-right text-xs tabular-nums text-zinc-800">
        {formatValue(club.squad_value)}
      </td>

      <td className="px-3 py-2 text-right text-xs font-semibold tabular-nums text-zinc-900">
        {formatValue(club.net_worth)}
      </td>

      <td className="px-3 py-2 text-right">
        <Link
          to={`/clubs/${club.id}`}
          aria-label={`Ver clube ${club.name}`}
          className="inline-flex items-center gap-1 text-xs font-medium whitespace-nowrap text-zinc-900 hover:underline"
        >
          Ver clube
          <svg
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth={1.8}
            strokeLinecap="round"
            strokeLinejoin="round"
            className="size-3.5"
            aria-hidden="true"
          >
            <path d="M9 6l6 6-6 6" />
          </svg>
        </Link>
      </td>
    </tr>
  );
}

export function ClubsPage() {
  const {
    data: clubs = [],
    isPending,
    isError,
    error,
    refetch,
  } = useQuery<Club[], Error>({
    queryKey: ["clubs"],
    queryFn: listClubs,
  });

  return (
    <Container>
      <header className="flex flex-wrap items-center justify-between gap-4 my-4">
        <div>
          <h1 className="text-2xl font-semibold tracking-tight">Clubs</h1>
          <p className="mt-1 text-sm text-zinc-500">
            Cada clube começa com {formatValue(INITIAL_CASH)} em caixa e sem jogadores. A liga
            aceita no máximo {MAX_CLUBS} clubes.
          </p>
        </div>

        {!isPending && !isError && (
          <span className="rounded-lg border border-zinc-200 px-3 py-1.5 text-xs text-zinc-600">
            {clubs.length} de {MAX_CLUBS} clubes
          </span>
        )}
      </header>

      <section className="overflow-hidden rounded-lg border border-zinc-200 bg-white">
        {isPending && (
          <div role="status" className="px-4 py-12 text-center text-sm text-zinc-500">
            Carregando clubes...
          </div>
        )}

        {isError && (
          <div role="alert" className="flex flex-col items-center gap-3 px-4 py-12 text-center">
            <p className="text-sm text-red-600">Não foi possível carregar os clubes.</p>
            <p className="text-xs text-zinc-500">{error.message}</p>
            <button
              type="button"
              onClick={() => refetch()}
              className="rounded-lg border border-zinc-200 px-3 py-2 text-xs font-medium hover:bg-zinc-50"
            >
              Tentar novamente
            </button>
          </div>
        )}

        {!isPending && !isError && (
          <>
            <div className="overflow-x-auto">
              <table className="w-full min-w-175 border-collapse text-left">
                <thead className="bg-zinc-50">
                  <tr>
                    {COLUMNS.map((column) => (
                      <th
                        key={column.label}
                        scope="col"
                        className={`h-9 border-b border-zinc-200 px-3 text-[11px] font-medium whitespace-nowrap text-zinc-500 ${
                          column.align === "right" ? "text-right" : ""
                        }`}
                      >
                        {"srOnly" in column ? (
                          <span className="sr-only">{column.label}</span>
                        ) : (
                          column.label
                        )}
                      </th>
                    ))}
                  </tr>
                </thead>

                <tbody className="divide-y divide-zinc-200">
                  {clubs.map((club) => (
                    <ClubRow key={club.id} club={club} />
                  ))}
                </tbody>
              </table>
            </div>

            {clubs.length === 0 && (
              <div className="px-4 py-12 text-center text-sm text-zinc-500">
                Nenhum clube cadastrado.
              </div>
            )}
          </>
        )}
      </section>
    </Container>
  );
}
