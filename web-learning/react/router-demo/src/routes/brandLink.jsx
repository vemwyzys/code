import { Link, useSearchParams } from "react-router-dom";

export function BrandLink({ brand, ...props }) {
    let [params] = useSearchParams()
    let isActive = params.getAll('brand').includes(brand)
    return (
        <Link
            style={{ color: isActive ? 'red' : '' }}
            to={`/shoes?brand=${brand}`}
            {...props}
        />
    )
}